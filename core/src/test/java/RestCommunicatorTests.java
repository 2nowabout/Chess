import org.junit.Test;
import restApi.RestCommunicator;

import static org.junit.Assert.assertTrue;

public class RestCommunicatorTests {
    private RestCommunicator communicator;

    @Test
    public void registerUser()
    {
        communicator = new RestCommunicator();
        try {
            assertTrue(communicator.Login("testuser123", "testerwithapassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
