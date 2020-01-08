package restShared;

public class AccountDTO {
    private String username;
    private String password;
    private String email;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String email) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {return email; }
}