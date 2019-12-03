package Websockets;

import State.GameStateManager;
import Websockets.handlers.enemyMoveHandler;
import Websockets.handlers.handler;
import Websockets.handlers.startHandler;
import org.json.JSONObject;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class handlerContext {
    private static Map<String, handler> handlers;

    static {
        handlers = new HashMap<>();
        handlers.put("enemymove", new enemyMoveHandler());
        handlers.put("found", new startHandler());
    }

    public static void processMessage(JSONObject json, GameStateManager gsm) {
        String handler = null;
        try {
            handler = json.getString("task");
        } catch (Exception e) {
            e.printStackTrace();
        }
        handlers.get(handler).handleMessage(json, gsm);
    }
}
