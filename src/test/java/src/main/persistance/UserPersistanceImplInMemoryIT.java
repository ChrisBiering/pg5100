package src.main.persistance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.main.model.User;
import src.main.model.UserType;

import static org.junit.Assert.*;

/**
 * Created by Chris on 10/10/15.
 */
public class UserPersistanceImplInMemoryIT {

    private UserPersistanceImplInMemory userPersistanceImpl;

    @Before
    public void setUp() throws Exception {
        userPersistanceImpl = new UserPersistanceImplInMemory();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateUser() throws Exception {
        User createdUser = userPersistanceImpl.createUser("createUser@test.com", "pw", UserType.Student);
        assertNotNull(createdUser);
        assertTrue(userPersistanceImpl.getUsers().contains(createdUser));
    }

    @Test
    public void testUpdateUser() throws Exception {
        fail();
    }

    @Test
    public void testGetUser() throws Exception {
        fail();
    }

    @Test
    public void testGetUsers() throws Exception {
        fail();
    }

    @Test
    public void testDeleteUser() throws Exception {
        fail();
    }
}