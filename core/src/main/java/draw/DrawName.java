package draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawName {
    private BitmapFont font;
    public DrawName(BitmapFont font)
    {
        this.font = font;
        font.getData().setScale(2, 2);
    }

    public void Draw(SpriteBatch sb, String name)
    {
        font.draw(sb, name, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
    }
}
