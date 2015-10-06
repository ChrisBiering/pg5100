package src.main.model;

import javax.inject.Inject;

/**
 * Created by Chris on 05/10/15.
 */
public class User {
    private int id;
    private String email;
    private UserType userType;
    private String password;

    @Inject
    public User(String email, String password, UserType userType) {
        this.email = email;
        this.userType = userType;
        this.password = password;
    }
}
