package Interfaces;

import SaveLibraries.Position;
import State.GameStateManager;

import java.awt.*;
import java.util.ArrayList;

public interface iGameChecks {
    boolean openSettingsCheck(iButtons settingsButton, Rectangle mouseRectangle, boolean isopen);
    void closeApp();
    void checkTile(iTile tile, ArrayList<Position> canMovePosition, float dt);
    void checkKings(GameStateManager gsm, ArrayList<iTile> bord);
    void checkKingsdead(GameStateManager gsm, ArrayList<iTile> bord);
}
