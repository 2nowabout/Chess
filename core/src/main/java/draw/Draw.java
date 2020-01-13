package draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Draw {
    private BitmapFont font;
    private DrawColor color;
    private DrawName name;
    private DrawWins wins;

    public Draw()
    {
        font = new BitmapFont();
        color = new DrawColor(font);
        name = new DrawName(font);
        wins = new DrawWins(font);
    }
    public void DrawColor(SpriteBatch sb, boolean isWhite)
    {
        color.draw(sb, isWhite);
    }
    public void DrawName(SpriteBatch sb, String name)
    {
        this.name.Draw(sb, name);
    }
    public void DrawWin(SpriteBatch sb, boolean blackwin, boolean whitewin) {this.wins.drawWin(sb, blackwin, whitewin);}

}
