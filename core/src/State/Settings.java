package State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Settings extends State {

    private GameState oldState;
    public Settings(GameStateManager gsm, GameState oldstate) {
        super(gsm);
        oldState = oldstate;

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

    }

    @Override
    public void dispose() {

    }
}
