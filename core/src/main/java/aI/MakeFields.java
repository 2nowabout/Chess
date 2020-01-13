package aI;

import interfaces.iTile;
import objects.Tile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class MakeFields {
    public List<iTile> doMoveAndMakeField(List<iTile> bord, Moves move) {
        ArrayList<iTile> newBord = BotMakeFakeBordCopy.generateFakeBord(bord);
        for (iTile tile : newBord) {
            if (move.getTile().getX() == tile.getX() && move.getTile().getY() == tile.getY()) {
                tile.setChesspieces(move.getChesspieces());
            }
        }
        for (iTile tile : newBord) {
            if (move.getChesspieces().getX() == tile.getX() && move.getChesspieces().getY() == tile.getY()) {
                tile.removeChestpiece();
            }
            tile.update(1);
        }
        return newBord;
    }
}
