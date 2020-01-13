import ChessLogin.ChessLogin;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTests {
    ChessLogin login;

    @Test
    public void successfulLogin()
    {
        login = new ChessLogin();
        assertTrue(login.login("odin2001", "huts"));
    }

    @Test
    public void failedLoginSamePassword()
    {
        login = new ChessLogin();
        assertFalse(login.login("hackerman", "huts"));
    }
    @Test
    public void failedLoginSameUsername()
    {
        login = new ChessLogin();
        assertFalse(login.login("odin2001", "Hacker"));
    }
    @Test
    public void failedLogin()
    {
        login = new ChessLogin();
        assertFalse(login.login("hackerman", "Hacked123"));
    }
}
