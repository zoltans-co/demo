package com.example.demo.controllers;

import com.example.demo.entities.ToDo;
import com.example.demo.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/todos")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<ToDo>> getToDo(@PathVariable final Long userId) {
        return new ResponseEntity<>(toDoService.getToDoForUser(userId), HttpStatus.OK);
    }

    @GetMapping(path = "{userId}/{toDoId}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("userId") Long userId, @PathVariable("toDoId") Long id) {
        return new ResponseEntity<>(toDoService.getToDoByUserAndId(userId, id), HttpStatus.OK);
    }

    @PostMapping
    public void createToDo(@RequestBody final ToDo toDo) {
        toDoService.createToDo(toDo);
    }

    @PutMapping("{userId}/{toDoId}")
    public ResponseEntity<ToDo> updateToDo(
            @PathVariable final Long userId, @PathVariable final Long toDoId, @RequestBody final ToDo toDo) {
        try {
            return null;
            //return new ResponseEntity<>(toDoService.updateToDoWithId(userId, toDoId, toDo), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "{toDoId}")
    public ResponseEntity<Void> deleteToDoById(@PathVariable("toDoId") Long id) {
        try {
            toDoService.deleteToDoById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
