package Websockets;

import org.json.JSONObject;

import javax.websocket.Session;
import java.io.IOException;

class MessageSender {
    private Session session;
    MessageSender(Session session)
    {
        this.session = session;
    }

    void sendMessage(JSONObject json)
    {
        try {
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
