package objects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import interfaces.iTile;
import saveLibraries.Position;

import java.util.ArrayList;

public class Bishop extends Chesspieces {
    private boolean teamhit = false;
    private boolean firstenemy = true;

    public Bishop(boolean white, int x, int y) {
        super(white, x, y);
        fieldPoints = generator.BishopPoints();
        points = 30;
    }

    @Override
    public void calculateMoves(ArrayList<iTile> tiles) {
        ArrayList<Position> actuallypossible = new ArrayList<>();
        boolean check1 = true;
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = false;
        for (int again = 0; again < 4; again++) {
            for (int i = 1; i < 9; i++) {
                for (iTile tile : tiles) {
                    if (check1) {
                        if (tile.getX() == x + i && tile.getY() == y + i) {
                            actuallypossible = checkTile(tile, actuallypossible, x + i, y + i);
                        }
                    } else if (check2) {
                        if (tile.getX() == x + i && tile.getY() == y - i) {
                            actuallypossible = checkTile(tile, actuallypossible, x + i, y - i);
                        }
                    } else if (check3) {
                        if (tile.getX() == x - i && tile.getY() == y + i) {
                            actuallypossible = checkTile(tile, actuallypossible, x - i, y + i);
                        }
                    } else if (check4) {
                        if (tile.getX() == x - i && tile.getY() == y - i) {
                            actuallypossible = checkTile(tile, actuallypossible, x - i, y - i);
                        }
                    }
                }
            }
            if (check1) {
                check2 = true;
                check1 = false;
                teamhit = false;
                firstenemy = true;
            } else if (check2) {
                check3 = true;
                check2 = false;
                teamhit = false;
                firstenemy = true;
            } else if (check3) {
                check4 = true;
                check3 = false;
                teamhit = false;
                firstenemy = true;
            }
            else
            {
                teamhit = false;
                firstenemy = true;
            }
        }
        possibleMoves = actuallypossible;
    }

    @Override
    public void loadTextures() {
        if (white) {
            texture = new Texture("WhiteBishop.png");
        } else {
            texture = new Texture("BlackBishop.png");
        }
    }

    private ArrayList<Position> checkTile(iTile tile, ArrayList<Position> actuallypossiblebegin, int newx, int newy) {
        ArrayList<Position> actuallypossible = actuallypossiblebegin;
        if (newx < 0 || newy < 0 || newx > 10 || newy > 10) {
            // outside field
        } else if (tile.hasChesspiece() && !teamhit && firstenemy) {
            if (tile.getChesspieces().white && white || !tile.getChesspieces().white && !white) {
                teamhit = true;
            } else if (tile.getChesspieces().white && !white || !tile.getChesspieces().white && white) {
                Position pos = new Position(newx, newy);
                actuallypossible.add(pos);
                firstenemy = false;
            }
        } else if (!tile.hasChesspiece() && !teamhit && firstenemy) {
            Position pos = new Position(newx, newy);
            actuallypossible.add(pos);
        }
        return actuallypossible;
    }
}
