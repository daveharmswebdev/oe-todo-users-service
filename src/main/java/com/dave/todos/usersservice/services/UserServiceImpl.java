package com.dave.todos.usersservice.services;

import com.dave.todos.usersservice.api.v1.mapper.UserMapper;
import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.api.v2.model.LastNameDTO;
import com.dave.todos.usersservice.domain.User;
import com.dave.todos.usersservice.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserDTO userDTO = userMapper.userToUserDto(user);
                    userDTO.setUserUrl("/api/v1/todos/" + user.getId());
                    return userDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public Page<User> getUsersPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<UserDTO> getUserPage(int pageSize, int page) {
        Pageable pageable = PageRequest.ofSize(pageSize).withPage(page);

        return this.getUsersPage(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public Page<UserDTO> getUserPageByLastName(int pageSize, int page, String lastName) {
        Pageable pageable = PageRequest.ofSize(pageSize).withPage(page).withSort(Sort.by("lastName"));

        return userRepository.findByLastNameContainingIgnoreCase(pageable, lastName).map(userMapper::userToUserDto);
    }

    @Override
    public UserDTO getUserById(Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    UserDTO userDTO = userMapper.userToUserDto(user);
                    userDTO.setUserUrl("/api/v1/todos/" + user.getId());
                    return userDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDTO createNewUser(UserDTO userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return saveAndReturnDto(user);
    }

    @Override
    public UserDTO saveUserByDto(Long id, UserDTO userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setId(id);
        return saveAndReturnDto(user);
    }

    @Override
    public List<LastNameDTO> getAllLastNames(String lastName) {
        Pageable pageable = PageRequest.ofSize(10).withPage(0).withSort(Sort.by("lastName"));

        return userRepository
                .findByLastNameContainingIgnoreCase(pageable, lastName)
                .map(userMapper::userToLastDTO)
                .getContent();
    }

    @Override
    public void deleteUserById(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
    }

    private UserDTO saveAndReturnDto(User user) {
        User savedUser = userRepository.save(user);

        UserDTO returnDto = userMapper.userToUserDto(savedUser);

        returnDto.setUserUrl("/api/v1/users/" + savedUser.getId());

        return returnDto;
    }
}
