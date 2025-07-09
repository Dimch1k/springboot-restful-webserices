package com.example.springbootrestfulwebserices.service.impl;

import com.example.springbootrestfulwebserices.dto.UserDto;
import com.example.springbootrestfulwebserices.entity.User;
import com.example.springbootrestfulwebserices.exception.ResourceAlreadyExistsException;
import com.example.springbootrestfulwebserices.exception.ResourceNotFoundException;
import com.example.springbootrestfulwebserices.repository.UserRepository;
import com.example.springbootrestfulwebserices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper  modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User", "email", user.getEmail());
        }
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByID(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user -> modelMapper.map(user, UserDto.class))).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User updatedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        userRepository.save(updatedUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.deleteById(id);
    }
}
