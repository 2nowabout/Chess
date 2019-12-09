package checks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class closeAppCheck {

    public void closeApp()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {  //exit game if escape is pressed
            Gdx.app.exit();
        }
    }
}
