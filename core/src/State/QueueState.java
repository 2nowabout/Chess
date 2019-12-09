package State;

import Websockets.Websocket;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QueueState extends State {

    private BitmapFont font;
    private Websocket client;
    private boolean matchFound = false;
    private boolean firstToPlay;

    public QueueState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        client = new Websocket();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        if(matchFound)
        {
            gsm.push(new GameState(gsm, firstToPlay, client));
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "Waiting for opponent", Gdx.graphics.getHeight() /2, (Gdx.graphics.getWidth()/2) - 30);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public void setMatchFound(boolean matchFound) {
        this.matchFound = matchFound;
    }
    public void setFirstToFire(boolean firstToFire)
    {
        this.firstToPlay = firstToFire;
    }
}
