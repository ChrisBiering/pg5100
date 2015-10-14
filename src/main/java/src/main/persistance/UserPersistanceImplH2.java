package src.main.persistance;

import src.main.model.User;
import src.main.model.UserType;
import src.main.persistance.qualifier.H2;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Chris on 05/10/15.
 */

//@Alternative
public class UserPersistanceImplH2 implements UserPersistence {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Inject @H2
    public UserPersistanceImplH2() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    public void tearDown() {
        entityManager.close();
    }

    public User createUser(String email, String password, UserType userType) {
        User user = new User(email, password, userType);
        entityManager.persist(user);
        return user;
    }

    public void updateUser(int userId, String newEmail) {
        User user = entityManager.find(User.class, userId);
        user.setEmail(newEmail);
    }

    public User getUser(int userId) {
        User user = entityManager.find(User.class, userId);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query query = entityManager.createQuery("SELECT u FROM Users u");
        return (List<User>)query.getResultList();
    }

    public boolean deleteUser(int userId) {
        entityManager.remove(entityManager.contains(getUser(userId)) ? getUser(userId) : entityManager.merge(getUser(userId)));
        return getUser(userId) == null;
    }

    @AroundInvoke
    public Object transactionInterceptor(InvocationContext context) throws Exception {
        entityManager.getTransaction().begin();
        try {
            return context.proceed();
        } finally {
            entityManager.getTransaction().commit();
        }
    }
}
