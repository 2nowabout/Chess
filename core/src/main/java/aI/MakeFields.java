package aI;

import interfaces.iTile;
import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class MakeFields {
    public List<iTile> doMoveAndMakeField(List<iTile> bord, Moves move) {
        List<iTile> newbord = new ArrayList<>(bord);
        for (iTile tile : newbord) {
            if (move.getTile().getX() == tile.getX() && move.getTile().getY() == tile.getY()) {
                tile.setChesspieces(move.getChesspieces());
            }
        }
        for (iTile tile : newbord) {
            if (move.getChesspieces().getX() == tile.getX() && move.getChesspieces().getY() == tile.getY()) {
                tile.removeChestpiece();
            }
        }
        return newbord;
    }
}
