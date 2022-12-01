package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Table(name = "todo_item")
@Entity(name = "ToDoItem")
public class ToDo {

    @Id
    @SequenceGenerator(
            name = "todo_sequence",
            sequenceName = "todo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "todo_sequence"
    )
    private Long id;

    private String title;
    private String description;
    private boolean completed;
    private String dueDate;

    @ManyToOne
    @JoinColumn(
            name = "userId",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_todo_item_fk"
            )
    )
    private AppUser userId;

    public ToDo(AppUser appUser, String title, String description, boolean completed, String dueDate) {
        this.userId = appUser;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
    }
}
