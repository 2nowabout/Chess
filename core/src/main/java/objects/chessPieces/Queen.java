package objects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import interfaces.iTile;
import saveLibraries.Position;

import java.util.ArrayList;

public class Queen extends Chesspieces {

    private boolean teamhit = false;
    private boolean firstenemy = true;

    public Queen(boolean white, int x, int y) {
        super(white, x, y);
        fieldPoints = generator.QueenPoints();
        if (white) {
            points = 90;
        } else {
            points = -90;
        }
    }

    @Override
    public void calculateMoves(ArrayList<iTile> tiles) {
        ArrayList<Position> actuallypossible = new ArrayList<>();
        boolean[] checks = {true, false, false, false, false, false, false, false};
        for (int again = 0; again < 8; again++) {
            for (int i = 1; i < 9; i++) {
                for (iTile tile : tiles) {
                    if (again < 4) {
                        actuallypossible = bishopPositions(actuallypossible, checks, i, tile);
                    } else {
                        actuallypossible = rookPositions(actuallypossible, checks, i, tile);
                    }
                }
            }

            if (checks[again]) {
                if (again != 7) {
                    checks[again + 1] = true;
                }
                checks[again] = false;
                teamhit = false;
                firstenemy = true;
            }
        }

        possibleMoves = actuallypossible;
    }

    @Override
    public void loadTextures() {
        if (white) {
            texture = new Texture("WhiteQueen.png");
        } else {
            texture = new Texture("BlackQueen.png");
        }
    }

    private ArrayList<Position> rookPositions(ArrayList<Position> actuallypossible, boolean[] checks, int i, iTile tile) {
        //Rook
        if (checks[4]) {
            if (tile.getX() == x + i && tile.getY() == y) {
                actuallypossible = checkTile(tile, actuallypossible, x + i, y);
            }
        } else if (checks[5]) {
            if (tile.getX() == x - i && tile.getY() == y) {
                actuallypossible = checkTile(tile, actuallypossible, x - i, y);
            }
        } else if (checks[6]) {
            if (tile.getX() == x && tile.getY() == y + i) {
                actuallypossible = checkTile(tile, actuallypossible, x, y + i);
            }
        } else if (checks[7]) {
            if (tile.getX() == x && tile.getY() == y - i) {
                actuallypossible = checkTile(tile, actuallypossible, x, y - i);
            }
        }
        return actuallypossible;
    }

    private ArrayList<Position> bishopPositions(ArrayList<Position> actuallypossible, boolean[] checks, int i, iTile tile) {
        //Bishop
        if (checks[0]) {
            if (tile.getX() == x + i && tile.getY() == y + i) {
                actuallypossible = checkTile(tile, actuallypossible, x + i, y + i);
            }
        } else if (checks[1]) {
            if (tile.getX() == x + i && tile.getY() == y - i) {
                actuallypossible = checkTile(tile, actuallypossible, x + i, y - i);
            }
        } else if (checks[2]) {
            if (tile.getX() == x - i && tile.getY() == y + i) {
                actuallypossible = checkTile(tile, actuallypossible, x - i, y + i);
            }
        } else if (checks[3]) {
            if (tile.getX() == x - i && tile.getY() == y - i) {
                actuallypossible = checkTile(tile, actuallypossible, x - i, y - i);
            }
        }
        return actuallypossible;
    }

    private ArrayList<Position> checkTile(iTile tile, ArrayList<Position> actuallypossiblebegin, int newx, int newy) {
        ArrayList<Position> actuallypossible = actuallypossiblebegin;
        if (newx < 0 || newy < 0 || newx > 9 || newy > 9) {
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
