package Websockets.handlers;

import State.GameStateManager;
import org.json.JSONObject;

public interface handler {
    void handleMessage(JSONObject json, GameStateManager gsm);
}
