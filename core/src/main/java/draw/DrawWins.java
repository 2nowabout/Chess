package draw;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawWins {

    public BitmapFont font;
    public DrawWins(BitmapFont font)
    {
        this.font = font;        font.getData().setScale(3, 3);
    }
    public void drawWin(SpriteBatch sb, boolean blackwin, boolean whitewin)
    {
        if(blackwin)
        {
            font.draw(sb, "Black Wins!", 100, 300);
        }
        if(whitewin)
        {
            font.draw(sb, "White Wins!", 100, 300);
        }
    }
}
