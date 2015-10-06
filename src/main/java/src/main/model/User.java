package src.main.model;

/**
 * Created by Chris on 05/10/15.
 */
public class User {
    private int id;
    private String email;
    private UserType userType;
    private String password;

    public User(String email, String password, UserType userType) {
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }
}
