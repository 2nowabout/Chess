package aI;

import interfaces.iTile;
import objects.Tile;
import objects.chessPieces.Chesspieces;

import java.util.ArrayList;
import java.util.List;

public class BotMakeFakeBordCopy {
    public static ArrayList<iTile> generateFakeBord(List<iTile> bord)
    {
        ArrayList<iTile> newbord = new ArrayList<>();
        for (iTile tile: bord) {
            iTile toAddTile = new Tile(0,0, tile.getX(), tile.getY());
            if(tile.hasChesspiece()) {
                Chesspieces chesspieces = tile.getChesspieces().clone(tile.getChesspieces());
                toAddTile.setChesspieces(chesspieces);
            }
            newbord.add(toAddTile);
        }
        return newbord;
    }
}
