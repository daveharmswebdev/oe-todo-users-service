package com.dave.todos.usersservice.services;

import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.api.v2.model.LastNameDTO;
import com.dave.todos.usersservice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    Page<User> getUsersPage(Pageable pageable);

    Page<UserDTO> getUserPage(int pageSize, int page);

    Page<UserDTO> getUserPageByLastName(int pageSize, int page, String lastName);

    UserDTO getUserById(Long id);

    UserDTO createNewUser(UserDTO userDto);

    UserDTO saveUserByDto(Long id, UserDTO userDto);

    List<LastNameDTO> getAllLastNames(String lastName);

    void deleteUserById(Long id);
}
