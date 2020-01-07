import ChessLogin.ChessLogin;

public class LoginTest {
    public static void main(String[] args) {
        ChessLogin login = new ChessLogin();
        if(login.login("odin2001", "huts"))
        {
            System.out.println("Correct");
        }
        else
        {
            System.out.println("InCorrect");
        }
    }
}
