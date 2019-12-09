package websockets.websocket;

import org.json.JSONObject;
import state.GameStateManager;
import websockets.handlers.enemyMoveHandler;
import websockets.handlers.handler;
import websockets.handlers.startHandler;

import java.util.HashMap;
import java.util.Map;

public class handlerContext {
    private static Map<String, handler> handlers;
    private static GameStateManager gsm;

    static {
        handlers = new HashMap<>();
        handlers.put("enemymove", new enemyMoveHandler());
        handlers.put("found", new startHandler());
    }

    public handlerContext(GameStateManager gsm)
    {
        this.gsm = gsm;
    }

    public static void processMessage(JSONObject json) {
        String handler = null;
        try {
            handler = json.getString("task");
        } catch (Exception e) {
            e.printStackTrace();
        }
        handlers.get(handler).handleMessage(json, gsm);
    }
}
