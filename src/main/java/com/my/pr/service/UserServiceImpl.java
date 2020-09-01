package com.my.pr.service;

import com.my.pr.dao.UserDao;
import com.my.pr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    @Autowired
    public void setUserDAO(UserDao userDao) {
        this.userDao = userDao;
    }
    @Transactional
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    public void edit(User user) {
        userDao.edit(user);
    }

    @Transactional
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    @Override
    public User loadUserByUsername(String s) {
        return userDao.findByName(s);
    }
}
