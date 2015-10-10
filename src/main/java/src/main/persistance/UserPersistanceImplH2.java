package src.main.persistance;

import src.main.model.User;
import src.main.model.UserType;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Chris on 05/10/15.
 */

@Alternative
public class UserPersistanceImplH2 implements UserPersistence {

    private EntityManagerFactory entityManagerFactory;

    @Inject
    public UserPersistanceImplH2() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    }

    public User createUser(String email, String password, UserType userType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = new User(email, password, userType);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    public void updateUser(int userId, String newEmail) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, userId);
        entityManager.getTransaction().begin();
        user.setEmail(newEmail);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public User getUser(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, userId);
        entityManager.close();
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM Users u");
        return (List<User>)query.getResultList();
    }

    public boolean deleteUser(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(getUser(userId)) ? getUser(userId) : entityManager.merge(getUser(userId)));
        entityManager.getTransaction().commit();
        entityManager.close();
        return getUser(userId) == null;
    }
}
