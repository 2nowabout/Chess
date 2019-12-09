package checks.gameChecks;

import Interfaces.iButtons;
import Interfaces.iGameChecks;
import Interfaces.iTile;
import SaveLibraries.Position;
import State.GameStateManager;

import java.awt.*;
import java.util.ArrayList;

public class gameChecks implements iGameChecks {
    private openSettingsCheck settingsCheck;
    private closeAppCheck closeAppCheck;
    private checkTileForMoveAllowed tileMoveCheck;
    private checkKingsCheck checkKingsCheck;
    private checkKingsDead checkKingsDead;

    private boolean whiteWon = false;
    private boolean blackWon = false;

    public gameChecks() {
        settingsCheck = new openSettingsCheck();
        closeAppCheck = new closeAppCheck();
        tileMoveCheck = new checkTileForMoveAllowed();
        checkKingsCheck = new checkKingsCheck();
        checkKingsDead = new checkKingsDead();
    }

    public boolean openSettingsCheck(iButtons settingsButton, Rectangle mouseRectangle, boolean isopen) {
        if (!isopen) {
            return settingsCheck.checkSettings(settingsButton, mouseRectangle);
        }
        return true;
    }

    public void closeApp() {
        closeAppCheck.closeApp();
    }

    public void checkTile(iTile tile, ArrayList<Position> canMovePosition, float dt) {
        tileMoveCheck.checkTile(tile, canMovePosition, dt);
    }

    public void checkKings(GameStateManager gsm, ArrayList<iTile> bord) {
        if (checkKingsCheck.checkKings(gsm, bord, true)) {
            blackWon = true;
        } else if (checkKingsCheck.checkKings(gsm, bord, false)) {
            whiteWon = true;
        }
    }

    public void checkKingsdead(GameStateManager gsm, ArrayList<iTile> bord) {
        if (checkKingsDead.checkWin(gsm, bord, true)) {
            blackWon = true;
        } else if (checkKingsDead.checkWin(gsm, bord, false)) {
            whiteWon = true;
        }
    }

    public boolean hasWhiteWon() { return whiteWon; }
    public boolean hasBlackWon() { return blackWon; }
}
