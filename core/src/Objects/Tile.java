package Objects;

import Objects.ChessPieces.Chesspieces;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Tile {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    public boolean hasChesspiece = false;
    public boolean canMoveHere = false;
    private Texture possibleTexture;
    private Texture texture;
    private Rectangle rectangle;
    private int renderX;
    private int renderY;
    private int x;
    private int y;
    private String postiontext;
    private BitmapFont font;
    private Chesspieces chesspieces;
    private Character[] vertical = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public Tile(int renderX, int renderY, int x, int y, String texture) {
        possibleTexture = new Texture("PossibleTile.png");
        font = new BitmapFont();
        this.renderX = renderX;
        this.renderY = renderY;
        this.x = x;
        this.y = y;
        this.texture = new Texture(texture);
        postiontext = vertical[y].toString() + (x + 1);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, renderX, renderY, WIDTH, HEIGHT);
        font.draw(batch, postiontext, (renderX + (texture.getWidth() / 2)), (renderY + (texture.getHeight() / 2)));
        if (chesspieces != null) {
            if(!chesspieces.isDead()) {
                chesspieces.render(batch, renderX, renderY, WIDTH, HEIGHT);
            }
        }
        if (canMoveHere) {
            batch.draw(possibleTexture, renderX, renderY, WIDTH, HEIGHT);
        }
    }

    public void update(float dt) {
        if (chesspieces != null) {
            chesspieces.update(dt, renderX, renderY);
        }
        rectangle = new Rectangle(renderX, renderY, 100, 100);
    }
    public void removeChestpiece() {
        this.chesspieces = null;
        hasChesspiece = false;
    }
    public Chesspieces getChesspieces() {
        return chesspieces;
    }

    public void setChesspieces(Chesspieces chesspieces) {
        this.chesspieces = chesspieces;
        hasChesspiece = true;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public Rectangle getRectangle() { return rectangle; }
    public void updateChessPiece() {
        if (chesspieces != null) {
            chesspieces.updateXandY(x, y);
        }
    }
}