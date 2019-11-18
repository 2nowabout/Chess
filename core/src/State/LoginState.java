package State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.*;

public class LoginState extends State {

    private TextField field;
    private Stage stage;

    public LoginState(GameStateManager gsm) {
        super(gsm);
       /* stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        com.badlogic.gdx.scenes.scene2d.ui.TextField textField = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        stage.addActor(textField);*/
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        stage.act();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
