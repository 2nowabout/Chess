package Objects;

import Interfaces.iButtons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Buttons implements iButtons {

    private static final int HEIGHT = 120;
    private static final int WIDTH = 400;

    private int height;
    private int width;

    private Rectangle rectangle;

    private Texture texture;
    private int renderX;
    private int renderY;
    private BitmapFont font;
    private String text;


    public Buttons(int renderX, int renderY, String text, int height, int width, String texture) {
        this.height = height;
        this.width = width;
        this.text = text;
        font = new BitmapFont();
        this.renderX = renderX;
        this.renderY = renderY;
        if (texture == "") {
            this.texture = new Texture("Button.png");
        } else {
            this.texture = new Texture(texture);
        }
    }

    public void update(float dt) {
        if (width == 0 || height == 0) {
            rectangle = new Rectangle(renderX, renderY, WIDTH, HEIGHT);
        } else {
            rectangle = new Rectangle(renderX, renderY, width, height);
        }
    }

    public void render(SpriteBatch sb) {
        if (width == 0 || height == 0) {
            sb.draw(texture, renderX, renderY, WIDTH, HEIGHT);
        } else {
            sb.draw(texture, renderX, renderY, width, height);
        }
        font.draw(sb, text, (renderX + ((texture.getWidth() / 5) * 2)), (renderY + (texture.getHeight() / 2)));
    }

    public void dispose() {
        texture.dispose();
        font.dispose();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public String getString() {
        return text;
    }
}
