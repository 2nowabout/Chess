package Objects.ChessPieces;

import Objects.Tile;
import SaveLibraries.Postition;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class King extends Chesspieces {

    public King(boolean white, int x, int y) {
        super(white, x, y);
        if (white) {
            texture = new Texture("WhiteKing.png");
        } else {
            texture = new Texture("BlackKing.png");
        }
        isKing = true;
    }

    @Override
    public void calculateMoves(List<Tile> tiles) {
        Postition pos = new Postition(x + 1, y);
        possibleMoves.add(pos);
        pos = new Postition(x-1, y);
        possibleMoves.add(pos);
        pos = new Postition(x, y+1);
        possibleMoves.add(pos);
        pos = new Postition(x, y-1);
        possibleMoves.add(pos);
        pos = new Postition(x-1, y+1);
        possibleMoves.add(pos);
        pos = new Postition(x-1, y-1);
        possibleMoves.add(pos);
        pos = new Postition(x+1, y+1);
        possibleMoves.add(pos);
        pos = new Postition(x+1, y-1);
        possibleMoves.add(pos);
        possibleMoves.removeAll(notPossibleMoves(tiles));
    }


    private List<Postition> notPossibleMoves(List<Tile> tiles) {
        List<Postition> notPossibleMoves = new ArrayList<>();
        for (Postition pos : possibleMoves) {
            for (Tile tile : tiles) {
                if (pos.getX() == tile.getX() && pos.getY() == tile.getY()) {
                    if(tile.hasChesspiece)
                    {
                        if(white && tile.getChesspieces().white || !white && !tile.getChesspieces().white)
                        {
                            notPossibleMoves.add(pos);
                        }
                    }
                }
            }
        }
        return notPossibleMoves;
    }

    public boolean checkCheckmate(List<Tile> tiles)
    {
        List<Chesspieces> enemys = new ArrayList<>();
        List<Postition> allmovesenemy = new ArrayList<>();
        List<Postition> allkingmoves;
        for (Tile tile: tiles) {
            if(tile.hasChesspiece) {
                if (white) {
                    if(!tile.getChesspieces().white)
                    {
                        enemys.add(tile.getChesspieces());
                    }
                } else {
                    if(tile.getChesspieces().white)
                    {
                        enemys.add(tile.getChesspieces());
                    }
                }
            }
        }
        for (Chesspieces chess: enemys) {
            chess.calculateMoves(tiles);
            allmovesenemy.addAll(chess.possibleMoves);
        }
        calculateMoves(tiles);
        allkingmoves = possibleMoves;
        for (Postition kingmoves: possibleMoves) {
            for (Postition enemymoves: allmovesenemy) {
                if(kingmoves.getX() == enemymoves.getX() && kingmoves.getY() == enemymoves.getY())
                {
                    allkingmoves.remove(kingmoves);
                }
            }
        }
        if(allkingmoves.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
