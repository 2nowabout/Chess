package objects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import interfaces.iTile;
import objects.ChessPiecesFunctions.FieldPointsGenerator;
import saveLibraries.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class Pawn extends Chesspieces {

    private boolean firstmove = true;

    public Pawn(boolean white, int x, int y) {
        super(white, x, y);
        isPawn = true;
        if (white) {
            points = 10;
        } else {
            points = -10;
        }
    }

    @Override
    public void calculateMoves(ArrayList<iTile> tiles) {
        boolean otherhit = false;
        for (int i = 1; i < 3; i++) {
            for (iTile tile : tiles) {
                if (firstmove) {
                    if (!white) {
                        if (tile.getX() == x && tile.getY() == y + i) {
                            if (!tile.hasChesspiece() && !otherhit) {
                                Position postion = new Position(x, y + i);
                                possibleMoves.add(postion);
                            } else if (tile.hasChesspiece()) {
                                otherhit = true;
                            }
                        }
                    } else {
                        if (tile.getX() == x && tile.getY() == y - i) {
                            if (!tile.hasChesspiece() && !otherhit) {
                                Position postion = new Position(x, y - i);
                                possibleMoves.add(postion);
                            } else if (tile.hasChesspiece()) {
                                otherhit = true;
                            }
                        }
                    }
                } else {
                    if (!white) {
                        if (tile.getX() == x && tile.getY() == y + 1) {
                            if (!tile.hasChesspiece() && !otherhit) {
                                Position position = new Position(x, y + 1);
                                possibleMoves.add(position);
                            }
                        }
                    } else {
                        if (tile.getX() == x && tile.getY() == y - 1) {
                            if (!tile.hasChesspiece() && !otherhit) {
                                Position position = new Position(x, y - 1);
                                possibleMoves.add(position);
                            }
                        }
                    }
                }
                if (!white) {
                    if (tile.getX() == x + 1 && tile.getY() == y + 1) {
                        if (tile.hasChesspiece() && tile.getChesspieces().white) {
                            Position position = new Position(x + 1, y + 1);
                            possibleMoves.add(position);
                        }
                    } else if (tile.getX() == x - 1 && tile.getY() == y + 1) {
                        if (tile.hasChesspiece() && tile.getChesspieces().white) {
                            Position position = new Position(x - 1, y + 1);
                            possibleMoves.add(position);
                        }
                    }
                } else {
                    if (tile.getX() == x + 1 && tile.getY() == y - 1) {
                        if (tile.hasChesspiece() && !tile.getChesspieces().white) {
                            Position position = new Position(x + 1, y - 1);
                            possibleMoves.add(position);
                        }
                    } else if (tile.getX() == x - 1 && tile.getY() == y - 1) {
                        if (tile.hasChesspiece() && !tile.getChesspieces().white) {
                            Position position = new Position(x - 1, y - 1);
                            possibleMoves.add(position);
                        }
                    }
                }
            }
        }
        possibleMoves = new ArrayList<>(new HashSet<>(possibleMoves));
    }

    @Override
    public void loadTextures() {
        if (white) {
            texture = new Texture("WhitePawn.png");
        } else {
            texture = new Texture("BlackPawn.png");
        }
    }

    @Override
    public ArrayList<ArrayList<Double>> getFieldPoints() {
        return FieldPointsGenerator.PawnPoints();
    }

    public void setFirstmove(boolean firstmove) {
        this.firstmove = firstmove;
    }
}

