/*package Websockets;

import java.net.URI;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Websocket {
    public static void main(String[] args) {
        URI uri = URI.create("ws://localhost:8095/zeeslag/");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                // Attempt Connect
                Session session = container.connectToServer(WebsocketEndpoint.class, uri);
                // Send a message
                session.getBasicRemote().sendText("Hello");
                // Close session
                Thread.sleep(10000);
                session.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}*/
