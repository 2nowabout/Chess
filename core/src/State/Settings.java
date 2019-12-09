package State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Settings extends State {

    private Texture white;
    private State oldState;
    public Settings(GameStateManager gsm, State oldstate) {
        super(gsm);
        oldState = oldstate;
        white = new Texture("WhiteSeeThrough.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(white, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {

    }
}
