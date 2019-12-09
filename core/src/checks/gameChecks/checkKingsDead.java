package checks.gameChecks;

import Interfaces.iTile;
import State.GameState;
import State.GameStateManager;

import java.util.ArrayList;

public class checkKingsDead {
    public boolean checkWin(GameStateManager gsm, ArrayList<iTile> bord, boolean isWhite) {
        boolean white = false;
        boolean black = false;
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
