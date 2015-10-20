package src.main.persistance;

import src.main.model.User;
import src.main.model.UserType;
import src.main.persistance.qualifier.H2;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.interceptor.*;
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
        closeEntityManagerAndFactory();
    }

    //TODO Find a way to make Interceptor work
    public User createUser(String email, String password, UserType userType) {
        entityManager.getTransaction().begin();
        User user = new User(email, password, userType);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
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

    //TODO Find a way to make Interceptor work
    public boolean deleteUser(int userId) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(getUser(userId)) ? getUser(userId) : entityManager.merge(getUser(userId)));
        entityManager.getTransaction().commit();
        return getUser(userId) == null;
    }

    public void closeEntityManagerAndFactory() {
        entityManager.close();
        entityManagerFactory.close();
    }
/*
    @AroundInvoke
    public Object transactionInterceptor(InvocationContext context) throws Exception {
        if(context.getMethod().getName().equals("createUser") || context.getMethod().getName().equals("deleteUser"))
            return context.proceed();

        entityManager.getTransaction().begin();
        System.out.println("Transaction begin " + context.getMethod().getName());
        try {
            return context.proceed();
        } finally {
            entityManager.getTransaction().commit();
            System.out.println("Transaction commit " + context.getMethod().getName());
        }
    }
    */
}
