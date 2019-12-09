package checks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import interfaces.iButtons;

import java.awt.*;

public class openSettingsCheck {

    public boolean checkSettings(iButtons settingsButton, Rectangle mouseRectangle)
    {
        if (settingsButton.getRectangle().intersects(mouseRectangle) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                return true;
        }
        return false;
    }
}
