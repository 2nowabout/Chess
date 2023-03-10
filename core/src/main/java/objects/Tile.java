package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import interfaces.iTile;
import objects.ChessPiecesFunctions.TilePositionGenerator;
import objects.chessPieces.Chesspieces;
import objects.chessPieces.King;

import java.awt.*;

public class Tile implements iTile {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private boolean hasChesspiece = false;
    private boolean canMoveHere = false;
    private Texture possibleTexture;
    private Texture kingChecked;
    private Texture texture;
    private Rectangle rectangle;
    private int renderX;
    private int renderY;
    private int x;
    private int y;
    private static BitmapFont font = new BitmapFont();
    private Chesspieces chesspieces;

    public Tile(int renderX, int renderY, int x, int y) {
        this.renderX = renderX;
        this.renderY = renderY;
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, renderX, renderY, WIDTH, HEIGHT);
        font.draw(batch, TilePositionGenerator.getPositionString(x,y), (renderX + (texture.getWidth() / 2)), (renderY + (texture.getHeight() / 2)));
        if (chesspieces != null) {
            if(!chesspieces.isDead()) {
                chesspieces.render(batch, renderX, renderY, WIDTH, HEIGHT);
            }
            if(chesspieces.isKing())
            {
                King king = (King) chesspieces;
                if(king.isChecked())
                {
                    batch.draw(kingChecked, renderX, renderY, WIDTH, HEIGHT);
                }
            }
        }
        if (canMoveHere) {
            batch.draw(possibleTexture, renderX, renderY, WIDTH, HEIGHT);
        }


    }

    public void update(float dt) {
        if (chesspieces != null) {
            chesspieces.update(dt, renderX, renderY, this.x, this.y);
        }
    }
    public void removeChestpiece() {
        this.chesspieces = null;
        hasChesspiece = false;
    }
    public Chesspieces getChesspieces() {
        return chesspieces;
    }
    public void dispose()
    {
        texture.dispose();
        possibleTexture.dispose();
        font.dispose();
        if(chesspieces != null)
        {
            chesspieces.dispose();
        }
    }

    public void setChesspieces(Chesspieces chesspieces) {
        this.chesspieces = chesspieces;
        hasChesspiece = true;
    }
    public boolean hasChesspiece() { return hasChesspiece; }
    public void setHasChesspiece(boolean hasChesspiece) { this.hasChesspiece = hasChesspiece; }
    public boolean isCanMoveHere() { return canMoveHere; }
    public void setCanMoveHere(boolean canMoveHere) { this.canMoveHere = canMoveHere; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Rectangle getRectangle() { return rectangle; }
    public void setTextures(String texture)
    {
        possibleTexture = new Texture("PossibleTile.png");
        this.texture = new Texture(texture);
        kingChecked = new Texture("kingChecked.png");
        rectangle = new Rectangle(renderX, renderY, 100, 100);
    }
    public void removeTextures()
    {
        possibleTexture = null;
        texture = null;
        kingChecked = null;
        rectangle = null;
    }
}
