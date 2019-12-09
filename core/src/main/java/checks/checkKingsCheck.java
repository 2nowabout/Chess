package checks;

import interfaces.iTile;
import objects.chessPieces.King;
import state.GameStateManager;

import java.util.ArrayList;

public class checkKingsCheck {
    public boolean checkKings(GameStateManager gsm, ArrayList<iTile> bord, boolean isWhite){
        for (iTile tile : bord) {
            if (tile.hasChesspiece()) {
                if (tile.getChesspieces().isKing()) {
                    King king = (King) tile.getChesspieces();
                    if (king.getColor() == isWhite) {
                        king.checkChecked(bord);
                        return king.checkCheckmate(bord);
                    }
                }
            }
        }
        return false;
    }
}
