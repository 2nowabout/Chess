package Objects.ChessPieces;

import Objects.Tile;
import SaveLibraries.Postition;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Pawn extends Chesspieces {

    private boolean firstmove = true;

    public Pawn(boolean white, int x, int y) {
        super(white, x, y);
        if (white) {
            texture = new Texture("WhitePawn.png");
        } else {
            texture = new Texture("BlackPawn.png");
        }
        isPawn = true;
    }

    @Override
    public void calculateMoves(List<Tile> tiles) {
        boolean otherhit = false;
        for (int i = 1; i < 3; i++) {
            for (Tile tile : tiles) {
                if(firstmove) {
                    if (!white) {
                        if (tile.getX() == x && tile.getY() == y + i) {
                            if (!tile.hasChesspiece && !otherhit) {
                                Postition postion = new Postition(x, y + i);
                                possibleMoves.add(postion);
                            } else if (tile.hasChesspiece) {
                                otherhit = true;
                            }
                        }
                    } else {
                        if (tile.getX() == x && tile.getY() == y - i) {
                            if (!tile.hasChesspiece && !otherhit) {
                                Postition postion = new Postition(x, y - i);
                                possibleMoves.add(postion);
                            } else if (tile.hasChesspiece) {
                                otherhit = true;
                            }
                        }
                    }
                }
                else
                {
                    if(!white)
                    {
                        if (tile.getX() == x && tile.getY() == y + 1) {
                            if (!tile.hasChesspiece && !otherhit) {
                                Postition postition = new Postition(x,  y+1);
                                possibleMoves.add(postition);
                            }
                        }
                    }
                    else
                    {
                        if (tile.getX() == x && tile.getY() == y - 1) {
                            if (!tile.hasChesspiece && !otherhit) {
                                Postition postition = new Postition(x,  y-1);
                                possibleMoves.add(postition);
                            }
                        }
                    }
                }
                if (!white) {
                    if (tile.getX() == x + 1 && tile.getY() == y + 1) {
                        if (tile.hasChesspiece && tile.getChesspieces().white) {
                            Postition postition = new Postition(x + 1, y+1);
                            possibleMoves.add(postition);
                        }
                    } else if (tile.getX() == x - 1 && tile.getY() == y + 1) {
                        if (tile.hasChesspiece && tile.getChesspieces().white) {
                            Postition postition = new Postition(x-1, y+1);
                            possibleMoves.add(postition);
                        }
                    }
                } else {
                    if (tile.getX() == x + 1 && tile.getY() == y - 1) {
                        if (tile.hasChesspiece&& !tile.getChesspieces().white) {
                            Postition postition = new Postition(x+1, y-1);
                            possibleMoves.add(postition);
                        }
                    } else if (tile.getX() == x - 1 && tile.getY() == y - 1) {
                        if (tile.hasChesspiece && !tile.getChesspieces().white) {
                            Postition postition = new Postition(x-1, y-1);
                            possibleMoves.add(postition);
                        }
                    }
                }
            }
        }
    }

    public void setFirstmove(boolean firstmove) { this.firstmove = firstmove; }
}

