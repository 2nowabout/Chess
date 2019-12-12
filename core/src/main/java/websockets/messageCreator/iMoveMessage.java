package websockets.messageCreator;

import interfaces.iTile;
import org.json.JSONObject;

public interface iMoveMessage {
    JSONObject moveMessage(iTile oldTile, iTile newTile, boolean isWhite);
}
