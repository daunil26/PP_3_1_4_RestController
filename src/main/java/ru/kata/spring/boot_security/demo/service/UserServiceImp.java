package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Transactional
@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;
//   private final PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
//      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public void add(User user) {
//      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userDao.add(user);
   }

   @Override
   public User updateUser(User user) {
//      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userDao.updateUser(user);
   }

   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void removeUserById(long id) {
      userDao.removeUserById(id);
   }

   @Override
   public User getUserById(long id) {
      User user = userDao.getUserById(id);
      return user;
   }

   @Override
   public User getUserByEmail(String Username) {
      User user = userDao.getUserByEmail(Username);
      return user;
   }

}
