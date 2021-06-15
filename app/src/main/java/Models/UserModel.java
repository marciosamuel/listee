package Models;

public class UserModel {
    private String user;
    private String email;
    private String password;

    public UserModel(String user, String email, String password){
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
