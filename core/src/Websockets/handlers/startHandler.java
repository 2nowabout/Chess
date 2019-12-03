package Websockets.handlers;

import State.GameStateManager;
import State.LoginState;
import org.json.JSONObject;

public class startHandler implements handler {
    @Override
    public void handleMessage(JSONObject json, GameStateManager gsm) {
        try {
            LoginState state = (LoginState) gsm.getCurrentState();
            boolean first = json.getBoolean("first");
            state.setFirstToFire(first);
            state.setMatchFound(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
