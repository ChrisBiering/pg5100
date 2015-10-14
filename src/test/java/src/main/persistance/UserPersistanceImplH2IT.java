package src.main.persistance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.main.model.User;
import src.main.model.UserType;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Chris on 08/10/15.
 */
public class UserPersistanceImplH2IT {

    private UserPersistanceImplH2 userPersistImpl;

    @Before
    public void setUp() throws Exception {
        userPersistImpl = new UserPersistanceImplH2();
    }

    @After
    public void tearDown() throws Exception {
        for(User user : userPersistImpl.getUsers()) {
            userPersistImpl.deleteUser(user.getId());
        }
        userPersistImpl.closeEntityManagerAndFactory();
    }

    @Test
    public void testCreateUser() throws Exception {
        User testUser = userPersistImpl.createUser("createUser@test.com", "Password1", UserType.Student);
        assertNotNull(testUser);
        assertTrue(testUser.getId() > 0);
    }

    @Test
    public void testGetUser() throws Exception {
        User expected = userPersistImpl.createUser("getUser@test.com", "Password1", UserType.Student);
        User actual = userPersistImpl.getUser(expected.getId());
        assertEquals(expected, actual);

        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    public void testUpdateUser() throws Exception {
        String oldEmail = "oldEmail@test.com";
        String newEmail = "newEmail@test.com";

        User expected = userPersistImpl.createUser(oldEmail, "Password1", UserType.Student);
        assertEquals(expected.getEmail(), oldEmail);

        userPersistImpl.updateUser(expected.getId(), newEmail);
        expected = userPersistImpl.getUser(expected.getId());
        assertEquals(expected.getEmail(), newEmail);
    }

    @Test
    public void testGetUsers() throws Exception {
        User createdUser = userPersistImpl.createUser("getUsers@test.com", "Password1", UserType.Teacher);
        List<User> users = userPersistImpl.getUsers();
        assertTrue(users.size() > 0);
        assertTrue(users.contains(createdUser));
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = userPersistImpl.createUser("deleteUsed@test.com", "Password1", UserType.Student);
        List<User> users = userPersistImpl.getUsers();
        assertTrue(users.size() > 0);
        assertTrue(userPersistImpl.deleteUser(user.getId()));
        users = userPersistImpl.getUsers();
        assertTrue(users.size() == 0);
    }
}