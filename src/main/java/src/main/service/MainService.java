package src.main.service;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import src.main.model.User;
import src.main.model.UserType;
import src.main.persistance.UserPersistence;
import src.main.persistance.qualifier.H2;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by Chris on 06/10/15.
 */
public class MainService {

    UserPersistence persistanceImpl;

    @Inject @H2
    public MainService(UserPersistence persistanceImpl) {
        this.persistanceImpl = persistanceImpl;
    }

    private void callService() {
        User user1 = persistanceImpl.createUser("email@main4.com", "Password1", UserType.Student);

        System.out.println("All users: ");
        for(User user : persistanceImpl.getUsers()) {
            System.out.println(user.toString());
        }


        System.out.println("\nUser info before email change: " + persistanceImpl.getUser(user1.getId()));
        System.out.println("Updating user email");
        persistanceImpl.updateUser(user1.getId(), "newEmail@main.com");
        System.out.println("User info after email change: " + persistanceImpl.getUser(user1.getId()));

        System.out.println("\nDeleting user");
        persistanceImpl.deleteUser(user1.getId());

        System.out.println("All users: ");
        for(User user : persistanceImpl.getUsers()) {
            System.out.println(user.toString());
        }

    }


    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<MainService> main = container.instance().select(MainService.class);
        main.get().callService();
    }

    static {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }
}
