package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
public class AdminRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getUsersList() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User add(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        if (userService.getUserById(id) == null) {
            throw new NoSuchElementException("User with id = " + id + " nof found.");
        } else {
            userService.removeUserById(id);
            return "User with id = " + id + " was deleted.";
        }
    }

}
