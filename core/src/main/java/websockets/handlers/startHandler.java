package websockets.handlers;

import org.json.JSONObject;
import state.GameStateManager;
import state.QueueState;

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
