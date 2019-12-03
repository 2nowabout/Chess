package checks.gameChecks;

import Interfaces.iTile;
import Objects.ChessPieces.King;
import State.GameState;
import State.GameStateManager;
import java.util.ArrayList;

public class checkKingsCheck {
    public void checkKings(GameStateManager gsm, ArrayList<iTile> bord){
        boolean whiteKingDead = false;
        boolean blackKingDead = false;
        for (iTile tile : bord) {
            if (tile.hasChesspiece()) {
                if (tile.getChesspieces().isKing()) {
                    King king = (King) tile.getChesspieces();
                    if (king.getColor()) {
                        king.checkChecked(bord);
                        whiteKingDead = king.checkCheckmate(bord);
                    } else if (!king.getColor()) {
                        king.checkChecked(bord);
                        blackKingDead = king.checkCheckmate(bord);
                    }
                }
            }
        }
/*        if (blackKingDead) {
            gsm.push(new GameState(gsm));
        }
        if (whiteKingDead) {
            gsm.push(new GameState(gsm));
        }*/
    }
}
