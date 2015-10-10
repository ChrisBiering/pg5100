package src.main.service;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import src.main.persistance.UserPersistence;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

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

    }


    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<MainService> main = container.instance().select(MainService.class);
        main.get().callService();
    }
}
