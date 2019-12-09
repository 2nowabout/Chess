package Websockets.handlers;

import State.GameStateManager;
import State.LoginState;
import State.QueueState;
import org.json.JSONObject;

public class startHandler implements handler {
    @Override
    public void handleMessage(JSONObject json, GameStateManager gsm) {
        try {
            QueueState state = (QueueState) gsm.getCurrentState();
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
