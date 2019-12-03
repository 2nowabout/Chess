package checks.gameChecks;

import Interfaces.iTile;
import State.GameState;
import State.GameStateManager;

import java.util.ArrayList;

public class checkKingsDead {
    public void checkWin(GameStateManager gsm, ArrayList<iTile> bord) {
        boolean white = false;
        boolean black = false;
        for (iTile tile : bord) {
            if (tile.hasChesspiece()) {
                if (tile.getChesspieces().isKing() && tile.getChesspieces().getColor()) {
                    white = true;
                }
                if (tile.getChesspieces().isKing() && !tile.getChesspieces().getColor()) {
                    black = true;
                }
            }
        }
        if (!white) {
            gsm.push(new GameState(gsm));
        }
        if (!black) {
            gsm.push(new GameState(gsm));
        }
    }
}
