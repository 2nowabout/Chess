package draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawColor {

    public BitmapFont font;
    public DrawColor(BitmapFont font)
    {
        this.font = font;
        font.getData().setScale(2, 2);
    }

    public void draw(SpriteBatch sb, boolean isWhite)
    {
        if (isWhite)
        {
            font.draw(sb, "You are white!", Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        }
        else
        {
            font.draw(sb, "You are black!", Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        }
    }
}
