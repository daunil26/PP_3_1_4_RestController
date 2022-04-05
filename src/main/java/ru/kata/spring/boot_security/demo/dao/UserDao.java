package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
   void removeUserById(long id);
   User getUserById(long id);
   User getUserByEmail(String Username);
   User updateUser(User user);
   void add(User user);
   List<User> listUsers();
}
