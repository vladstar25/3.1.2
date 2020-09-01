package com.my.pr.dao;

import com.my.pr.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    User findByName(String name);
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
}
