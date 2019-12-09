package interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import objects.chessPieces.Chesspieces;

import java.awt.*;

public interface iTile {
    void render(SpriteBatch batch);
    void update(float dt);
    void removeChestpiece();
    void dispose();
    Chesspieces getChesspieces();
    void setChesspieces(Chesspieces chesspieces);
    boolean hasChesspiece();
    void setHasChesspiece(boolean hasChesspiece);
    boolean isCanMoveHere();
    void setCanMoveHere(boolean canMoveHere);
    int getX();
    int getY();
    Rectangle getRectangle();
}
