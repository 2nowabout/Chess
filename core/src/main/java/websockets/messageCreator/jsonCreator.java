package websockets.messageCreator;

import interfaces.iTile;
import org.json.JSONObject;

public class jsonCreator implements iJsonCreator {
    public jsonCreator(){ }

    @Override
    public JSONObject checkMessage() {
        return null;
    }

    @Override
    public JSONObject moveMessage(iTile oldTile, iTile newTile, boolean isWhite) {
        JSONObject json = new JSONObject();
        try {
            json.put("task", "move");
            json.put("oldX", oldTile.getX());
            json.put("oldY", oldTile.getY());
            json.put("newX", newTile.getX());
            json.put("newY", newTile.getY());
            json.put("isWhite", isWhite);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public JSONObject winMessage(boolean isWhite, boolean won) {
        JSONObject json = new JSONObject();
        try
        {
            json.put("task", "win");
            json.put("win", true);
            json.put("isWhite", isWhite);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return json;
    }
}
