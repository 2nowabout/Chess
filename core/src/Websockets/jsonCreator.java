package Websockets;

import Interfaces.iTile;
import org.json.JSONObject;

import javax.websocket.Session;

public class jsonCreator {

    private MessageSender sender;

    public jsonCreator(Session session)
    {
        sender = new MessageSender(session);
    }

    public void moveCreator(iTile oldTile, iTile newTile, boolean isWhite)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("task", "move");
            json.put("oldX", oldTile.getX());
            json.put("oldY", oldTile.getY());
            json.put("newX", newTile.getX());
            json.put("newY", newTile.getY());
            json.put("isWhite", isWhite);
            sender.sendMessage(json);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
