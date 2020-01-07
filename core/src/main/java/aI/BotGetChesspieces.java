package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Position;

import java.util.ArrayList;
import java.util.List;
public class BotGetChesspieces {

    public List<Chesspieces> getAllyChessPieces(List<iTile> bord)
    {
        List<Chesspieces> allPieces = new ArrayList<>();
        for (iTile tile: bord) {
            if(tile.hasChesspiece())
            {
                if(!tile.getChesspieces().getColor()) {
                    Chesspieces toAdd = tile.getChesspieces();
                    allPieces.add(toAdd);
                }
            }
        }
        return allPieces;
    }
    public List<Chesspieces> getEnemyChesspieces(List<iTile> bord)
    {
        List<Chesspieces> allPieces = new ArrayList<>();
        for (iTile tile: bord) {
            if(tile.hasChesspiece())
            {
                if(tile.getChesspieces().getColor()) {
                    Chesspieces toAdd = tile.getChesspieces();
                    allPieces.add(toAdd);
                }
            }
        }
        return allPieces;
    }
}
