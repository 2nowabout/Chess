import ChessLogin.ChessLogin;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    ChessLogin register;

    @Test
    public void registerSuccessful() {
        register = new ChessLogin();
        try {
            assertFalse(register.login("testuser123", "testerwithpassword"));
            assertTrue(register.register("testuser123", "testerwithapassword", "iamnotmediaperson@gmail.com"));
            Thread.sleep(50);
            assertTrue(register.login("testuser123", "testerwithapassword"));
            assertTrue(register.removeUser("testuser123"));
            Thread.sleep(50);
            assertFalse(register.login("testuser123", "testerwithpassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
