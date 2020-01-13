package state;

import com.Chess;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.java_websocket.client.WebSocketClient;
import restApi.RestCommunicator;
import websockets.websocket.Websocket;

import java.awt.*;

public class LoginState extends State {

    private TextField field;
    private Texture background;
    private Stage stage;

    private BitmapFont font;
    private boolean loggedIn = false;
    private boolean failed = false;
    private boolean firstToPlay;
    private TextField txtUsername;
    private TextField txtPassword;
    private Button loginBtn;
    private Button registerStateBtn;
    private RestCommunicator restCommunicator;
    private Skin skin = new Skin(Gdx.files.internal("flat-earth-ui.json"));

    public LoginState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("white background.jpg");
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        restCommunicator = new RestCommunicator();
        createCanvas();
    }

    private void createCanvas(){
        skin.getFont("font").getData().setScale(1.33F);

        txtUsername = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        txtUsername.setSize(250, 40);
        txtUsername.setPosition(Gdx.graphics.getWidth() /2F - (txtUsername.getWidth()/2f), Gdx.graphics.getHeight()/2F - (txtUsername.getHeight()/2f));
        txtUsername.setMessageText("Username");
        stage.addActor(txtUsername);
        txtPassword = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        txtPassword.setSize(250, 40);
        txtPassword.setPosition(Gdx.graphics.getWidth() /2F - (txtPassword.getWidth()/2f), Gdx.graphics.getHeight() /2F - (txtPassword.getHeight()/2f) -60);
        txtPassword.setMessageText("Password");
        txtPassword.setPasswordCharacter('*');
        txtPassword.setPasswordMode(true);
        stage.addActor(txtPassword);
        loginBtn = new TextButton("Login", skin);
        loginBtn.setPosition(Gdx.graphics.getWidth() /2F - (loginBtn.getWidth()/2f), Gdx.graphics.getHeight()/2F - (loginBtn.getHeight()/2f) - 120);
        loginBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                loggedIn = restCommunicator.Login(txtUsername.getText(), txtPassword.getText());
                if(!loggedIn)
                {
                    failed = true;
                }
            }
        });
        stage.addActor(loginBtn);
        registerStateBtn = new TextButton("Register", skin);
        registerStateBtn.setPosition(Gdx.graphics.getWidth() /2F - (registerStateBtn.getWidth()/2f), Gdx.graphics.getHeight()/2F - (registerStateBtn.getHeight()/2f) - 180);
        registerStateBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.push(new RegisterState(gsm));

            }
        });
        stage.addActor(registerStateBtn);
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
        if(loggedIn)
        {
            Websocket client = new Websocket();
            try { Thread.sleep(0); }
            catch (Exception e) { e.printStackTrace();}
            gsm.push(new QueueState(gsm, client.getJsonCreator()));

        }
        handleInput();
        stage.act(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        stage.getBatch().begin();
        font.draw(stage.getBatch(), "Login", Gdx.graphics.getHeight() /2, (Gdx.graphics.getWidth()/2) - 30); //TODO not working
        if(failed)
        {
            font.draw(stage.getBatch(), "Wrong Credentials", (Gdx.graphics.getHeight()/6) * 5, Gdx.graphics.getWidth()/2 - 40); //TODO not working
        }
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
