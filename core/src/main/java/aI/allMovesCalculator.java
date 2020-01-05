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
            for (Position pos: chesspiece.getPossibleMoves()) {
                for (iTile tile: bord) {
                    if(tile.hasChesspiece() && tile.getX() == pos.getX() && tile.getY() == pos.getY())
                    {
                            CheckMoves.add(new Moves(tile.getChesspieces().getPoints(), tile, chesspiece));
                    }
                }
            }
            chesspiece.resetMoves();
        }
        return CheckMoves;
    }
}
