package src.main.persistance;

import src.main.model.User;
import src.main.model.UserType;

import java.util.ArrayList;

/**
 * Created by Chris on 05/10/15.
 */
public interface UserPersistence {

    User createUser(String email, String password, UserType userType);

    void updateUser(int userId, String newEmail);

    User getUser(int userId);

    ArrayList<User> getUsers();

    void deleteUser(int userId);

}
