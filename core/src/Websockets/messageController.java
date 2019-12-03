package Websockets;

import State.GameStateManager;
import org.json.JSONObject;

public class messageController {

    private static GameStateManager gsm;
    public messageController(GameStateManager gsm)
    {
        this.gsm = gsm;
    }

    public static void handleMessage(JSONObject json)
    {

    }
}
