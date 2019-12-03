package Websockets;

import State.GameStateManager;
import org.json.JSONObject;

public class messageController {

    private static GameStateManager gsm;
    public messageController(GameStateManager gsm)
    {
        messageController.gsm = gsm;
    }

    public static void handleMessage(JSONObject json)
    {
        handlerContext.processMessage(json, gsm);
    }
}
