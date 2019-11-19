package Objects.ChessPieces;

import Objects.Tile;
import SaveLibraries.Postition;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Chesspieces {

    private boolean teamhit = false;
    private boolean firstenemy = true;

    public Rook(boolean white, int x, int y) {
        super(white, x, y);
        if (white) {
            texture = new Texture("WhiteRook.png");
        } else {
            texture = new Texture("BlackRook.png");
        }
    }

    @Override
    public void calculateMoves(List<Tile> tiles) {
        List<Postition> actuallypossible = new ArrayList<>();
        boolean check1 = true;
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = false;
        for (int again = 0; again < 4; again++) {
            for (int i = 1; i < 9; i++) {
                for (Tile tile : tiles) {
                    if (check1) {
                        if (tile.getX() == x + i && tile.getY() == y) {
                            actuallypossible = checkTile(tile, actuallypossible, x + i, y);
                        }
                    } else if (check2) {
                        if (tile.getX() == x - i && tile.getY() == y) {
                            actuallypossible = checkTile(tile, actuallypossible, x - i, y);
                        }
                    } else if (check3) {
                        if (tile.getX() == x && tile.getY() == y + i) {
                            actuallypossible = checkTile(tile, actuallypossible, x, y + i);
                        }
                    } else if (check4) {
                        if (tile.getX() == x && tile.getY() == y - i) {
                            actuallypossible = checkTile(tile, actuallypossible, x, y - i);
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

    private List<Postition> checkTile(Tile tile, List<Postition> actuallypossiblebegin, int newx, int newy) {
        if (newx < 0 || newy < 0 || newx > 10 || newy > 10) {
            // outside field
        } else if (tile.hasChesspiece() && !teamhit && firstenemy) {
            if (tile.getChesspieces().white && white || !tile.getChesspieces().white && !white) {
                teamhit = true;
            } else if (tile.getChesspieces().white && !white || !tile.getChesspieces().white && white) {
                Postition pos = new Postition(newx, newy);
                actuallypossiblebegin.add(pos);
                firstenemy = false;
            }
        } else if (!tile.hasChesspiece() && !teamhit && firstenemy) {
            Postition pos = new Postition(newx, newy);
            actuallypossiblebegin.add(pos);
        }
        return actuallypossiblebegin;
    }
}

