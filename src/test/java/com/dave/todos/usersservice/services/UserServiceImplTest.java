package com.dave.todos.usersservice.services;

import com.dave.todos.usersservice.api.v1.mapper.UserMapper;
import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.domain.User;
import com.dave.todos.usersservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private static final Long ID = 1L;
    private static final String FIRST_NAME = "First";
    private static final String LAST_NAME = "Last";
    private static final String EMAIL = "yo@email.com";
    private static final String ADDRESS = "1 Address Road";
    private static final String POSTAL_ZIP = "12345";
    private static final String REGION = "Region";
    private static final String COUNTRY = "Country";

    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(UserMapper.INSTANCE, userRepository);
    }

    @Test
    void getAllUsers() throws Exception {
        List<User> users = Arrays.asList(new User(), new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOS = userService.getAllUsers();

        assertEquals(3, userDTOS.size());
    }

    @Test
    void getUserById() throws Exception {
        // given
        User user = new User();
        user.setId(ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setAddress(ADDRESS);
        user.setPostalZip(POSTAL_ZIP);
        user.setRegion(REGION);
        user.setCountry(COUNTRY);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // when
        UserDTO userDTO = userService.getUserById(ID);

        // then
        assertEquals(FIRST_NAME, userDTO.getFirstName());
        assertEquals(LAST_NAME, userDTO.getLastName());
        assertEquals(EMAIL, userDTO.getEmail());
        assertEquals(ADDRESS, userDTO.getAddress());
        assertEquals(POSTAL_ZIP, userDTO.getPostalZip());
        assertEquals(REGION, userDTO.getRegion());
        assertEquals(COUNTRY, userDTO.getCountry());
        assertEquals("/api/v1/todos/1", userDTO.getUserUrl());
    }

    @Test
    void createNewUser() {
        UserDTO userDTO = UserDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .address(ADDRESS)
                .postalZip(POSTAL_ZIP)
                .region(REGION)
                .country(COUNTRY)
                .userUrl("/api/v1/todos/1")
                .build();

        User newUser = User.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .address(ADDRESS)
                .postalZip(POSTAL_ZIP)
                .region(REGION)
                .country(COUNTRY)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        UserDTO updatedDTO = userService.createNewUser(userDTO);

        assertEquals(FIRST_NAME, updatedDTO.getFirstName());
        assertEquals(LAST_NAME, updatedDTO.getLastName());
        assertEquals(EMAIL, updatedDTO.getEmail());
        assertEquals(ADDRESS, updatedDTO.getAddress());
        assertEquals(POSTAL_ZIP, updatedDTO.getPostalZip());
        assertEquals(REGION, updatedDTO.getRegion());
        assertEquals(COUNTRY, updatedDTO.getCountry());
        assertEquals("/api/v1/users/1", updatedDTO.getUserUrl());
    }

    @Test
    void saveUserByDto() {
        UserDTO userDTO = UserDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .address(ADDRESS)
                .postalZip(POSTAL_ZIP)
                .region(REGION)
                .country(COUNTRY)
                .userUrl("/api/v1/todos/1")
                .build();

        User newUser = User.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .address(ADDRESS)
                .postalZip(POSTAL_ZIP)
                .region(REGION)
                .country(COUNTRY)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        UserDTO updatedDTO = userService.saveUserByDto(ID, userDTO);

        assertEquals(FIRST_NAME, updatedDTO.getFirstName());
        assertEquals(LAST_NAME, updatedDTO.getLastName());
        assertEquals(EMAIL, updatedDTO.getEmail());
        assertEquals(ADDRESS, updatedDTO.getAddress());
        assertEquals(POSTAL_ZIP, updatedDTO.getPostalZip());
        assertEquals(REGION, updatedDTO.getRegion());
        assertEquals(COUNTRY, updatedDTO.getCountry());
        assertEquals("/api/v1/users/1", updatedDTO.getUserUrl());
    }

    @Test
    void deleteUserById() {
        // given
        User user = new User();
        user.setId(ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setAddress(ADDRESS);
        user.setPostalZip(POSTAL_ZIP);
        user.setRegion(REGION);
        user.setCountry(COUNTRY);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userRepository.deleteById(ID);

        verify(userRepository, times(1)).deleteById(anyLong());
    }
}