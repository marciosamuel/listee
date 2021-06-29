package Models;

public class UserModel {
    private String id;
    private String user;
    private String email;
//    private String password;

    public UserModel(String id, String user, String email){
        this.id = id;
        this.user = user;
        this.email = email;
//        this.password = password;
    }

    public String getId() { return id; }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

//    public String getPassword() { return password; }
}
