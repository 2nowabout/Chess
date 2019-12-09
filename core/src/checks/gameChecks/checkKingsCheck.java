package checks.gameChecks;

import Interfaces.iTile;
import Objects.ChessPieces.King;
import State.GameState;
import State.GameStateManager;
import java.util.ArrayList;

public class checkKingsCheck {
    public boolean checkKings(GameStateManager gsm, ArrayList<iTile> bord, boolean isWhite){
        boolean whiteKingDead = false;
        boolean blackKingDead = false;
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
