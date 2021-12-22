package com.dave.todos.usersservice.services;

import com.dave.todos.usersservice.api.v1.mapper.UserMapper;
import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.domain.User;
import com.dave.todos.usersservice.repositories.UserRepository;
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
