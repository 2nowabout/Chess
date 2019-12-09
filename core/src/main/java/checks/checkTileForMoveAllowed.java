package checks;

import interfaces.iTile;
import saveLibraries.Position;

import java.util.ArrayList;

public class checkTileForMoveAllowed {

    public void checkTile(iTile tile, ArrayList<Position> canMovePosition, float dt)
    {
        if (!canMovePosition.isEmpty()) //check if list of positions is empty or filled
        {
            for (Position position : canMovePosition) {
                if (tile.getX() == position.getX() && tile.getY() == position.getY()) //check what tile has the position
                {
                    tile.setCanMoveHere(true); // make tile render a blue square to move too
                }
            }
        }
    }
}
