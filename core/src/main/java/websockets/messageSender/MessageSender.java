package websockets.messageSender;

import org.json.JSONObject;

import javax.websocket.Session;
import java.io.IOException;

public class MessageSender {
    private static Session session;
    public MessageSender(Session session)
    {
        this.session = session;
    }

    public static void broadcast(JSONObject json)
    {
        try {
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
