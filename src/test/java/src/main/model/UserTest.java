package src.main.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Chris on 14/10/15.
 */
public class UserTest {

    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testInvalidEmailCausesViolation() throws Exception {
        User user = new User("email.com", "Password1", UserType.Student);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testValidEmailCausesNoViolation() throws Exception {
        User user = new User("email@test.com", "Password1", UserType.Student);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testInvalidPasswordCausesViolation() throws Exception {
        //Password must contain a number as well as upper and lower case letters
        User user = new User("email@test.com", "Password", UserType.Student);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testValidPasswordCausesNoViolations() throws Exception {
        User user = new User("email@test.com", "Password1", UserType.Student);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testNullPasswordCausesViolation() throws Exception {
        User user = new User("email@test.com", null, UserType.Student);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testNullEmailCausesViolation() throws Exception {
        User user = new User(null, "Password1", UserType.Student);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertEquals(1, constraintViolations.size());
    }
}