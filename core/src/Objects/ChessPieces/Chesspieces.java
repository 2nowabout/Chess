package Objects.ChessPieces;

import Objects.Tile;
import SaveLibraries.Postition;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Chesspieces {
    protected Texture texture;

    protected boolean white;

    protected boolean isKing;

    protected boolean dead = false;

    protected int x;
    protected int y;

    protected boolean clicked = false;

    protected Rectangle rectangle;

    protected List<Postition> possibleMoves;

    public Chesspieces(boolean white, int x, int y)
    {
        this.white = white;
        this.x = x;
        this.y = y;
        isKing = false;
    }

    public abstract void calculateMoves(List<Tile> tiles);
    public void render(SpriteBatch batch, int renderX, int renderY, int width, int height) { batch.draw(texture, renderX, renderY, width, height); }
    public void update(float dt, int renderX, int renderY) {
        rectangle = new Rectangle(renderX, renderY, 100, 100);
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public List<Postition> getPossibleMoves() {
        return possibleMoves;
    }
    public void resetMoves()
    {
        possibleMoves = new ArrayList<>();
    }
    public void updateXandY(int x, int y) {  this.x = x;  this.y = y;  }
    public boolean getColor() { return white; }
    public boolean isDead() { return dead; }
    public void setDead(boolean dead) { this.dead = dead; }
    public boolean isKing() { return isKing; }
}
