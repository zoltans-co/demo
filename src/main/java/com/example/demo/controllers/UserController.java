package com.example.demo.controllers;

import com.example.demo.entities.AppUser;
import com.example.demo.services.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/users")
public class UserController {

    @Autowired
    private final AppUserService appUserService;

    @Operation(
            summary = "Register a new user",
            description = "This method will register a new user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppUser.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not created",
                            content = @Content
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AppUser> createUser(@RequestBody final AppUser appUser) {
        try {
            return ResponseEntity.ok(appUserService.createUser(appUser));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Get user by id",
            description = "This method will return a user by id",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppUser.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content
                    )
            }
    )
    @GetMapping("{userId}")
    public ResponseEntity<AppUser> getUser(@PathVariable final Long userId) {
        return ResponseEntity.ok(appUserService.getUserById(userId));
    }

    @Operation(
            summary = "Update user by id",
            description = "This method will update a user by id",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppUser.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content
                    )
            }
    )
    @PutMapping("{userId}")
    public ResponseEntity<AppUser> updateUser(@PathVariable final Long userId, @RequestBody final AppUser appUser) {
        try {
            return new ResponseEntity<>(appUserService.updateUserWithId(userId, appUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Delete user by id",
            description = "This method will delete a user by id",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppUser.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable final Long userId) {
        appUserService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

}
