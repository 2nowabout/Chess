package interfaces;

import java.util.ArrayList;
import java.util.List;

public interface iBot {
    void updateBord(ArrayList<iTile> tiles);
    void act();
    void shutdown();
}
