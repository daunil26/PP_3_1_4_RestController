package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String admin(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model users) {
        users.addAttribute("user", userService.getUserByEmail(user.getUsername()));
        users.addAttribute("userList", userService.listUsers());
        users.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping
    public String add(@ModelAttribute("user") User user, @RequestParam(value = "nameRoles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("user") User user,
                       @RequestParam(value = "editRoles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id,
                         @RequestParam(value = "editRoles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

}
