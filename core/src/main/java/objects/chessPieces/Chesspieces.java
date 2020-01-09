package objects.chessPieces;

import com.Chess;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import interfaces.iTile;
import saveLibraries.Position;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Chesspieces {
    protected Texture texture;
    protected boolean white;
    protected boolean isPawn;
    protected boolean isKing;
    protected boolean dead = false;
    protected boolean clicked = false;
    protected ArrayList<ArrayList<Integer>> field;

    protected int points;

    protected int x;
    protected int y;

    protected Rectangle rectangle;

    protected ArrayList<Position> possibleMoves;

    public Chesspieces(boolean white, int x, int y, ArrayList<ArrayList<Integer>> field)
    {
        this.field = field;
        this.white = white;
        this.x = x;
        this.y = y;
        isKing = false;
        isPawn = false;
        possibleMoves = new ArrayList<>();
    }

    public Chesspieces clone(Chesspieces piece){
        return ChessPieceClone.checkChessPiece(piece);
    }

    public abstract void calculateMoves(ArrayList<iTile> tiles);
    public abstract void loadTextures();
    public void removeTextures() { texture = null; }
    public void render(SpriteBatch batch, int renderX, int renderY, int width, int height) { batch.draw(texture, renderX, renderY, width, height); }
    public void update(float dt, int renderX, int renderY, int x, int y) { rectangle = new Rectangle(renderX, renderY, 100, 100); this.x = x; this.y = y; }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public ArrayList<Position> getPossibleMoves() {
        return possibleMoves;
    }
    public void resetMoves()
    {
        possibleMoves = new ArrayList<>();
    }
    public boolean getColor() { return white; }
    public boolean isDead() { return dead; }
    public void setDead(boolean dead) { this.dead = dead; }
    public boolean isKing() { return isKing; }
    public boolean isPawn() { return isPawn; }
    public void dispose() { texture.dispose(); }
    public int getPoints() { return points; }
    public int getX() { return x; }
    public int getY() { return y; }
}
