package restApi;

import com.google.gson.Gson;
import jdk.vm.ci.code.Register;
import org.json.JSONObject;
import restShared.AccountResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RestCommunicator {

    private HttpURLConnection conn;

    public boolean Login(String username, String password) {
        try {
            URL url = new URL("http://localhost:1234/auth/login");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject input = new JSONObject();
            input.put("username", username);
            input.put("password", password);
            OutputStream os = conn.getOutputStream();
            os.write(input.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                return false;
            } else {
                return getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Register(String username, String password, String email) {
        try {
            URL url = new URL("http://localhost:1234/auth/register");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject input = new JSONObject();
            input.put("username", username);
            input.put("password", password);
            input.put("email", email);
            OutputStream os = conn.getOutputStream();
            os.write(input.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                return false;
            } else {
                boolean complete = getValue();
                System.out.println(complete);
                return complete;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean getValue() {
        String token = null;
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()));

            StringBuilder re = new StringBuilder();
            String currentLine;

            while ((currentLine = in.readLine()) != null)
                token = re.append(currentLine).toString();

            in.close();
            Gson gson = new Gson();
            AccountResponse response = gson.fromJson(token, AccountResponse.class);

            if (response.isSuccess()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
