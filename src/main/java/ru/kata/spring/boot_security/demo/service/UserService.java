package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    User updateUser(User user);
    void removeUserById(int id);
    User getUserById(int id);
    User getUserByEmail(String Username);
    List<User> listUsers();
}
