package websockets.messageCreator;

import org.json.JSONObject;

public interface iWinMessage {
    JSONObject winMessage(boolean isWhite, boolean won);
}
