package Websockets;

import org.json.JSONObject;

import java.net.URI;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Websocket {
    private final static String uri = "ws://localhost:8096/Chess/";
    Session session;
    private jsonCreator jsonCreator;
    public Websocket(){
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                // Attempt Connect
                session = container.connectToServer(WebsocketEndpoint.class, new URI(uri));
                jsonCreator = new jsonCreator(session);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (Exception t) {
            t.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try
        {
            session.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public jsonCreator getJsonCreator() { return jsonCreator; }
}
