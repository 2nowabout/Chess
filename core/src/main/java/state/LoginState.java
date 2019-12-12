package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import websockets.websocket.Websocket;

import java.awt.*;

public class LoginState extends State {

    private TextField field;
    private Stage stage;

    private BitmapFont font;
    private Websocket client;
    private boolean matchFound = false;
    private boolean firstToPlay;

    public LoginState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        client = new Websocket();
       /* stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        com.badlogic.gdx.scenes.scene2d.ui.TextField textField = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        stage.addActor(textField);*/
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
            gsm.push(new GameState(gsm, firstToPlay, client.getJsonCreator()));
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

    }
}
