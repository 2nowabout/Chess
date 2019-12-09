package websockets.handlers;

import org.json.JSONObject;
import state.GameStateManager;

public interface handler {
    void handleMessage(JSONObject json, GameStateManager gsm);
}
