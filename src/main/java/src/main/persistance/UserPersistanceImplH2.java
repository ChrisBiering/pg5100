package src.main.persistance;

import src.main.model.User;
import src.main.model.UserType;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Chris on 05/10/15.
 */

@Alternative
public class UserPersistanceImplH2 implements UserPersistence {

    @Inject
    public UserPersistanceImplH2() {}

    public User createUser(String email, String password, UserType userType) {
        return null;
    }

    public void updateUser(int userId, String newEmail) {

    }

    public User getUser(int userId) {
        return null;
    }

    public ArrayList<User> getUsers() {
        return null;
    }

    public void deleteUser(int userId) {

    }
}
