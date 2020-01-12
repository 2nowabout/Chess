package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import restApi.RestCommunicator;

public class RegisterState extends State {

    private Texture background;
    private Stage stage;
    private TextField txtUsername;
    private TextField txtPassword;
    private TextField txtPasswordCheck;
    private TextField txtEmail;
    private Button loginBtn;
    private Button registerStateBtn;
    private RestCommunicator restCommunicator;
    private Skin skin = new Skin(Gdx.files.internal("flat-earth-ui.json"));

    public RegisterState(GameStateManager gsm) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        restCommunicator = new RestCommunicator();
        createCanvas();
        background = new Texture("Background1.jpg");
    }

    private void createCanvas(){
        skin.getFont("font").getData().setScale(1.33F);
        txtUsername = new TextField("", skin);
        txtUsername.setSize(250, 40);
        txtUsername.setPosition(Gdx.graphics.getWidth() /2F - (txtUsername.getWidth()/2f), Gdx.graphics.getHeight()/2F - (txtUsername.getHeight()/2f));
        txtUsername.setMessageText("Username");
        stage.addActor(txtUsername);
        txtPassword = new TextField("", skin);
        txtPassword.setSize(250, 40);
        txtPassword.setPosition(Gdx.graphics.getWidth() /2F - (txtPassword.getWidth()/2f), Gdx.graphics.getHeight()/2F - (txtPassword.getHeight()/2f)-60);
        txtPassword.setMessageText("Password");
        stage.addActor(txtPassword);
        txtEmail = new TextField("", skin);
        txtEmail.setSize(250,40);
        txtEmail.setPosition(Gdx.graphics.getWidth() /2F - (txtEmail.getWidth()/2f), Gdx.graphics.getHeight()/2F - (txtEmail.getHeight()/2f)+60);
        txtEmail.setMessageText("Email");
        stage.addActor(txtEmail);
        txtPasswordCheck = new TextField("", skin);
        txtPasswordCheck.setSize(250,40);
        txtPasswordCheck.setPosition(Gdx.graphics.getWidth() /2F - (txtPasswordCheck.getWidth()/2f), Gdx.graphics.getHeight()/2F - (txtPasswordCheck.getHeight()/2f)-120);
        txtPasswordCheck.setMessageText("Confirm Password");
        stage.addActor(txtPasswordCheck);
        loginBtn = new TextButton("Already have an account", skin);
        loginBtn.setPosition(Gdx.graphics.getWidth() /2F - (loginBtn.getWidth()/2f), Gdx.graphics.getHeight()/2F - (loginBtn.getHeight()/2f) - 240);
        loginBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.push(new LoginState(gsm));
            }
        });
        stage.addActor(loginBtn);
        registerStateBtn = new TextButton("Register", skin);
        registerStateBtn.setPosition(Gdx.graphics.getWidth() /2F - (registerStateBtn.getWidth()/2f), Gdx.graphics.getHeight()/2F - (registerStateBtn.getHeight()/2f) - 180);
        registerStateBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(txtPassword.getText().equals(txtPasswordCheck.getText())) { //TODO show warning if not equal
                    restCommunicator.Register(txtUsername.getText(), txtPassword.getText(), txtEmail.getText());
                }
            }
        });
        stage.addActor(registerStateBtn);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
