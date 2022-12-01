package com.example.demo.services;

import com.example.demo.entities.AppUser;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void createUser(final AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public AppUser getUserById(final Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public void deleteUserById(final Long userId) {
        appUserRepository.deleteById(userId);
    }

    public AppUser updateUserWithId(final Long userId, final AppUser appUser) {
        final AppUser user = appUserRepository.findById(userId).orElseThrow(()
                -> new NotFoundException("User not found with id: " + userId));
        user.setFirstName(appUser.getFirstName());
        user.setLastName(appUser.getLastName());
        user.setUsername(appUser.getUsername());
        user.setPassword(appUser.getPassword());
        return appUserRepository.save(user);
    }
}
