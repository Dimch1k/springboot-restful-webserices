package com.example.springbootrestfulwebserices.service;

import com.example.springbootrestfulwebserices.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserByID(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long id);
}
