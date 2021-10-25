package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;
import saveLibraries.Position;

import java.util.ArrayList;
import java.util.List;

public class allMovesCalculator {

    public List<Moves> calcAllMoves(List<Chesspieces> pieces, List<iTile> bord, double lastmove, boolean blackPlaying)
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
                        Moves toAdd = null;
                        if(tile.hasChesspiece())
                        {
                            if(tile.getX() == pos.getX() && tile.getY() == pos.getY())
                            {
                                toAdd = new Moves(tile.getChesspieces().getPoints(), tile, chesspiece);
                                toAdd.addPoints(lastmove);
                                if(blackPlaying){
                                    toAdd.addPoints(chesspiece.getFieldPoints().get(tile.getY()).get(tile.getX()));
                                }
                                else {
                                    toAdd.removePoints(chesspiece.getFieldPoints().get(7 - tile.getY()).get(7 - tile.getX()));
                                }

                                CheckMoves.add(toAdd);
                            }
                        }
                        else if (!tile.hasChesspiece() && tile.getX() == pos.getX() && tile.getY() == pos.getY()) {
                            toAdd = new Moves(0, tile, chesspiece);
                            toAdd.addPoints(lastmove);
                            if(blackPlaying){
                                toAdd.addPoints(chesspiece.getFieldPoints().get(tile.getY()).get(tile.getX()));
                            }
                            else {
                                toAdd.removePoints(chesspiece.getFieldPoints().get(7 - tile.getY()).get(7 - tile.getX()));
                            }
                            CheckMoves.add(toAdd);
                        }
                    }
                }
            }
            chesspiece.resetMoves();
        }
        return CheckMoves;
    }
}
