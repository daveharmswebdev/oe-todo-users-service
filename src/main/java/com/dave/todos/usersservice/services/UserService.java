package com.dave.todos.usersservice.services;

import com.dave.todos.usersservice.api.v1.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createNewUser(UserDTO userDto);

    UserDTO saveUserByDto(Long id, UserDTO userDto);

    void deleteUserById(Long id);
}
