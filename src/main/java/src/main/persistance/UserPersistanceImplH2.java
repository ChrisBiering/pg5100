package src.main.persistance;

import src.main.model.User;
import src.main.model.UserType;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Chris on 05/10/15.
 */

@Alternative
public class UserPersistanceImplH2 implements UserPersistence {

    @Inject
    DBHandler dbHandler;
    Connection connection;

    @Inject
    public UserPersistanceImplH2() {}

    public void setup()  {
        try {
            connection = dbHandler.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String email, String password, UserType userType) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute("INSERT INTO users (EMAIL, PASSWORD, USERTYPE)" +
                                " VALUES ('" + email + "', '" + password + "', '" + userType.toString() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int userId, String newEmail) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE users" +
                    "SET EMAIL = '" + newEmail + "'" +
                    "WHERE ID = " + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int userId) {
        Statement statement;
        User user = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE ID = " + userId);

            user = new User(rs.getString("EMAIL"), rs.getString("PASSWORD"), UserType.valueOf(rs.getString("USERTYPE")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");

            while(rs.next()) {
                users.add(new User(rs.getString("EMAIL"), rs.getString("PASSWORD"), UserType.valueOf(rs.getString("USERTYPE"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(int userId) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE ID = " + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
