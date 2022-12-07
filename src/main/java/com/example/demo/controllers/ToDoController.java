package com.example.demo.controllers;

import com.example.demo.entities.ToDo;
import com.example.demo.services.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/todos")
public class ToDoController {

    @Autowired
    private final ToDoService toDoService;

    @Operation(
            summary = "Create a new todo",
            description = "This method will create a new todo",
            tags = {"ToDos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Todo created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Todo not created",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody final ToDo toDo) {
        return ResponseEntity.ok(toDoService.createToDo(toDo));
    }

    @Operation(
            summary = "Get all todos of a user",
            description = "This method will return a todo by user id",
            tags = {"ToDos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Todo found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Todo not found",
                            content = @Content
                    )
            }
    )
    @GetMapping("{userId}")
    public ResponseEntity<List<ToDo>> getToDo(@PathVariable final Long userId) {
        return new ResponseEntity<>(toDoService.getToDoForUser(userId), HttpStatus.OK);
    }

    @Operation(
            summary = "Get todo by id",
            description = "This method will return a todo by id",
            tags = {"ToDos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Todo found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Todo not found",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "{toDoId}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("toDoId") Long id) {
        return new ResponseEntity<>(toDoService.getToDoById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Update todo by id",
            description = "This method will update a todo by id",
            tags = {"ToDos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Todo updated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Todo not updated",
                            content = @Content
                    )
            }
    )
    @PutMapping("{toDoId}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable final Long toDoId, @RequestBody final ToDo toDo) {
        try {
            return new ResponseEntity<>(toDoService.updateToDoWithId(toDoId, toDo), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Delete todo by id",
            description = "This method will delete a todo by id",
            tags = {"ToDos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Todo deleted",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Todo not deleted",
                            content = @Content
                    )
            }
    )
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
