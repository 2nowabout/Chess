package checks;

import interfaces.iTile;
import state.GameStateManager;

import java.util.ArrayList;

public class checkKingsDead {
    public boolean checkWin(GameStateManager gsm, ArrayList<iTile> bord, boolean isWhite) {
        for (iTile tile : bord) {
            if (tile.hasChesspiece()) {
                if (tile.getChesspieces().isKing() && tile.getChesspieces().getColor() == isWhite) {
                   return false;
                }
            }
        }
        return true;
    }
}
