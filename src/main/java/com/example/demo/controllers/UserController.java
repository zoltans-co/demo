package com.example.demo.controllers;

import com.example.demo.entities.AppUser;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class User {

    private final UserService userService;

    @Autowired
    public User(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody AppUser appUser) {
        userService.createUser(appUser);
    }

    @GetMapping
    public void getUser() {
        System.out.println("User retrieved");
    }

    @DeleteMapping
    public void deleteUser() {
        System.out.println("User deleted");
    }

    @PutMapping
    public void updateUser() {
        System.out.println("User updated");
    }

}
