package AI;

import Interfaces.iTile;
import Objects.ChessPieces.Chesspieces;
import SaveLibraries.Position;

import java.util.ArrayList;
import java.util.List;

public class allMovesCalculator {

    public List<Moves> calcAllMoves(List<Chesspieces> pieces, ArrayList<iTile> bord)
    {
        List<Moves> CheckMoves = new ArrayList<>();
        for (Chesspieces chesspiece: pieces) {
            chesspiece.calculateMoves(bord);
            for (Position pos: chesspiece.getPossibleMoves()) {
                for (iTile tile: bord) {
                    if(tile.getX() == pos.getX() && tile.getY() == pos.getY())
                    {
                        if(tile.hasChesspiece())
                        {
                            CheckMoves.add(new Moves(tile.getChesspieces().getPoints(), tile, chesspiece));
                        }
                    }
                }
            }
        }
        return CheckMoves;
    }
}
