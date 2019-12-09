package websockets.handlers;

import org.json.JSONObject;
import saveLibraries.Position;
import state.GameState;
import state.GameStateManager;

public class enemyMoveHandler implements handler {
    @Override
    public void handleMessage(JSONObject json, GameStateManager gsm) {
        try
        {
            int oldX = json.getInt("oldX");
            int oldY = json.getInt("oldY");
            int newX = json.getInt("newX");
            int newY = json.getInt("newY");
            Position oldPos = new Position(oldX, oldY);
            Position newPos = new Position(newX, newY);
            GameState state = (GameState) gsm.getCurrentState();
            state.enemyMove(newPos, oldPos);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
