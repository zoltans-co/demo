package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "AppUser")
@Table(name = "app_user")
public class AppUser {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(
            mappedBy = "userId",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<ToDo> toDoList = new ArrayList<>();

    public AppUser(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addToDoList(ToDo toDo) {
        toDoList.add(toDo);
    }
}
