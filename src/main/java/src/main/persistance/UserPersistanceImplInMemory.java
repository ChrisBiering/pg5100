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
public class UserPersistanceImplInMemory implements UserPersistence {

    ArrayList<User> allUsers = new ArrayList<User>();

    @Inject
    public UserPersistanceImplInMemory() {}

    public void createUser(String email, String password, UserType userType) {
        User user = new User(email, password, userType);
        allUsers.add(user);
    }

    public void updateUser(int userId, String newEmail) {
        User user = allUsers.get(userId);
        user.setEmail(newEmail);
    }

    public User getUser(int userId) {
        return allUsers.get(userId);
    }

    public ArrayList<User> getUsers() {
        return allUsers;
    }

    public void deleteUser(int userId) {
        allUsers.remove(userId);
    }

    public void setup() {}
}
