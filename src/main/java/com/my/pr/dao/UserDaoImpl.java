package com.my.pr.dao;

import com.my.pr.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> allUsers() {
        return entityManager.createQuery("from " + User.class.getName()).getResultList();
    }

    @Override
    public User findByName(String username) {
        User user = null;
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.name=:username");
        query.setParameter("username", username);
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {

        }
        return user;
    }

    public void add(User user) {
        entityManager.persist(user);
    }

    public void delete(User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));
    }

    public void edit(User user) {
        entityManager.merge(user);
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
}
