package com.example.demo.entities;

import com.example.demo.repositories.AppUserRepository;
import com.example.demo.repositories.ToDoRepository;
import com.example.demo.services.AppUserService;
import com.example.demo.services.ToDoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ToDoAppTest {

    private AppUserService appUserService;
    private ToDoService toDoService;

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private ToDoRepository toDoRepository;

    @BeforeEach
    void setUp() {
        appUserService = new AppUserService(appUserRepository);
        toDoService = new ToDoService(toDoRepository);
    }

    //@AfterEach
    void tearDown() {
        toDoRepository.deleteAll();
        toDoRepository.flush();
        appUserRepository.deleteAll();
        appUserRepository.flush();
    }

    //@Test
    void createUser() {
        // given
        final AppUser appUser = new AppUser("JohnDoe", "secret", "John", "Doe");
        appUser.addToDoList(new ToDo(appUser, "Buy milk", "Buy milk from the store", false, "2021-09-01"));
        appUser.addToDoList(new ToDo(appUser, "Buy eggs", "Buy eggs from the store", false, "2021-09-01"));
        appUser.addToDoList(new ToDo(appUser, "Buy cheese", "Buy cheese from the store", false, "2021-09-01"));

        // when
        appUserService.createUser(appUser);

        // then
        assertEquals(1, appUserRepository.count());
        assertEquals(3, toDoRepository.count());
    }

}