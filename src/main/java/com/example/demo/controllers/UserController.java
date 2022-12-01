package com.example.demo.controllers;

import com.example.demo.entities.AppUser;
import com.example.demo.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody final AppUser appUser) {
        appUserService.createUser(appUser);
    }

    @GetMapping("{userId}")
    public void getUser(@PathVariable final Long userId) {
        appUserService.getUserById(userId);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable final Long userId) {
        appUserService.deleteUserById(userId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<AppUser> updateUser(@PathVariable final Long userId, @RequestBody final AppUser appUser) {
        try {
            return new ResponseEntity<>(appUserService.updateUserWithId(userId, appUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
