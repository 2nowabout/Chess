package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;
import saveLibraries.Position;

import java.util.ArrayList;
import java.util.List;

public class allMovesCalculator {

    public List<Moves> calcAllMoves(List<Chesspieces> pieces, List<iTile> bord)
    {
        List<Moves> CheckMoves = new ArrayList<>();
        ArrayList<iTile> tiles = new ArrayList<>(bord);
        for (Chesspieces chesspiece: pieces) {
            chesspiece.resetMoves();
            chesspiece.calculateMoves(tiles);
            ArrayList<Position> chessPieceMoves = chesspiece.getPossibleMoves();
            if(!chessPieceMoves.isEmpty()) {
                for (Position pos : chessPieceMoves) {
                    for (iTile tile : bord) {
                        if(tile.hasChesspiece())
                        {
                            if(tile.getX() == pos.getX() && tile.getY() == pos.getY())
                            {
                                CheckMoves.add(new Moves(tile.getChesspieces().getPoints(), tile, chesspiece));
                            }
                        }
                        else if (!tile.hasChesspiece() && tile.getX() == pos.getX() && tile.getY() == pos.getY()) {
                            CheckMoves.add(new Moves(0, tile, chesspiece));
                        }
                    }
                }
            }
            chesspiece.resetMoves();
        }
        return CheckMoves;
    }
}
