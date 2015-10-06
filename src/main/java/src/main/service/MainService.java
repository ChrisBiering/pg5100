package src.main.service;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import src.main.model.User;
import src.main.model.UserType;
import src.main.persistance.UserPersistence;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Chris on 06/10/15.
 */
public class MainService {

    UserPersistence persistanceImpl;

    @Inject
    public MainService(UserPersistence persistanceImpl) {
        this.persistanceImpl = persistanceImpl;
    }

    private void callService() {
        persistanceImpl.createUser("abc@gmail.com", "pw", UserType.Student);
        ArrayList<User> users = persistanceImpl.getUsers();

        for(User user : users) {
            System.out.println(user.getEmail());
        }
    }

    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<MainService> main = container.instance().select(MainService.class);
        main.get().callService();
    }
}
