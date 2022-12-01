package com.example.demo.services;

import com.example.demo.entities.ToDo;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public void createToDo(final ToDo toDo) {
        toDoRepository.save(toDo);
    }

    public ToDo updateToDoWithId(final Long userId, final Long toDoId, final ToDo toDo) {
        final ToDo todo = toDoRepository.findByUserIdAndId(userId, toDoId).orElseThrow(()
                -> new NotFoundException("ToDo not found with id: " + toDoId));
        todo.setTitle(toDo.getTitle());
        todo.setDescription(toDo.getDescription());
        todo.setCompleted(toDo.isCompleted());
        todo.setDueDate(todo.getDueDate());
        return toDoRepository.save(todo);
    }

    public List<ToDo> getToDoForUser(final Long userId) {
        return toDoRepository.findByUserId(userId);
    }

    public ToDo getToDoByUserAndId(final Long userId, final Long id) {
        return toDoRepository.findByUserIdAndId(userId, id).orElseThrow(() ->
                new NotFoundException("ToDo not found with id: " + id));
    }

    public void deleteToDoById(final Long id) {
        toDoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDo not found with id: " + id));
        toDoRepository.deleteById(id);
    }
}