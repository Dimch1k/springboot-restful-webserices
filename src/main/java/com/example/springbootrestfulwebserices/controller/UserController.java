package com.example.springbootrestfulwebserices.controller;

import com.example.springbootrestfulwebserices.dto.UserDto;
import com.example.springbootrestfulwebserices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Code 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // http://localhost:8081/api/users/1
    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get user from the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Code 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto foundUser = userService.getUserByID(id);
        return new ResponseEntity<>(foundUser,  HttpStatus.OK);
    }

    // http://localhost:8081/api/users/
    @Operation(
            summary = "Get All User REST API",
            description = "Get All User REST API is used to get all users from the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Code 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update user in the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Code 200 SUCCESS"
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, @PathVariable Long id) {
        user.setId(id);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete user in the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Code 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }

}
