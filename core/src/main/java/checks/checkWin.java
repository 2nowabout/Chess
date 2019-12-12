package checks;

import interfaces.iGameChecks;
import org.json.JSONObject;
import websockets.messageCreator.iJsonCreator;
import websockets.messageSender.MessageSender;

public class checkWin {
    public void checkIfWon(boolean iswhite, iJsonCreator creator, iGameChecks checks) {
        JSONObject json;
        if (checks.hasWhiteWon() && iswhite || checks.hasBlackWon() && !iswhite) {
            json = creator.winMessage(iswhite, true);
        } else {
            json = creator.winMessage(iswhite, false);
        }
        MessageSender.broadcast(json);
    }
}
