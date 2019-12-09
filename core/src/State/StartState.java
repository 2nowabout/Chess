package State;

import Interfaces.iButtons;
import Objects.Buttons;
import checks.gameChecks.closeAppCheck;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StartState extends State {

    private GameStateManager gsm;
    private List<iButtons> buttons;
    private closeAppCheck closeCheck;

    public StartState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        buttons = new ArrayList<>();
        closeCheck = new closeAppCheck();

        Buttons localPlay = new Buttons(((Gdx.graphics.getWidth() / 5) * 2), ((Gdx.graphics.getHeight() / 5) * 4), "LocalPlay",0,0, "");
        Buttons singlePlayer = new Buttons(((Gdx.graphics.getWidth() / 5) * 2), ((Gdx.graphics.getHeight() / 5) * 3), "SinglePlayer", 0,0, "");
        Buttons multiPlayer = new Buttons(((Gdx.graphics.getWidth() / 5) * 2), ((Gdx.graphics.getHeight() / 5) * 2), "MultiPlayer", 0, 0, "");

        buttons.add(localPlay);
        buttons.add(singlePlayer);
        buttons.add(multiPlayer);
    }

    @Override
    protected void handleInput() {
        closeCheck.closeApp();
        for (iButtons button : buttons) {
            Rectangle mouseRectangle = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 1, 1); //get mouse position
            mouseRectangle.y = Gdx.graphics.getHeight() - mouseRectangle.y; // invert y, this is already inverted in the game
            if (button.getRectangle().intersects(mouseRectangle) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                switch (button.getString()) {
                    case "LocalPlay":
                        gsm.setLocalPlay(true);
                        gsm.setSinglePlayer(false);
                        gsm.push(new LocalGameState(gsm));
                        break;
                    case "SinglePlayer":
                        gsm.setLocalPlay(false);
                        gsm.setSinglePlayer(true);
                        gsm.push(new SinglePlayerGameState(gsm));
                        break;
                    case "MultiPlayer":
                        gsm.setLocalPlay(false);
                        gsm.setSinglePlayer(false);
                        gsm.push(new LoginState(gsm));
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        for (iButtons button : buttons) {
            button.update(dt);
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(new Texture("Background.jpg"), 0 , 0 , 1920, 1080);
        for (iButtons button : buttons) {
            button.render(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        for (iButtons button : buttons) {
            button.dispose();
        }
    }
}
