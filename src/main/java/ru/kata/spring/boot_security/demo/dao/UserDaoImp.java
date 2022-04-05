package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoImp implements UserDao {

   private final EntityManager entityManager;

   @Autowired
   public UserDaoImp(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   @Override
   public void add(User user) {
      entityManager.persist(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return entityManager.createQuery("FROM User", User.class).getResultList();
   }

   @Override
   public void removeUserById(long id) {
      entityManager.remove(entityManager.find(User.class, id));
   }

   @Override
   public User getUserById(long id) {
      return entityManager.find(User.class, id);
   }

   @Override
   public User getUserByEmail(String email) {
      List<User> userList = entityManager.createQuery(
                      "SELECT user FROM User user join fetch  user.roles WHERE user.email =:email", User.class)
              .setParameter("email", email)
              .getResultList();
      if (userList.isEmpty()) {
         return null;
      } else {
         return userList.get(0);
      }
   }

   @Override
   public User updateUser(User user) {
      return entityManager.merge(user);
   }

}
