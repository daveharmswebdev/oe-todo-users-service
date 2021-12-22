package com.dave.todos.usersservice.controllers.v1;

import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.controllers.AbstractRestController;
import com.dave.todos.usersservice.controllers.RestResponseEntityExceptionHandler;
import com.dave.todos.usersservice.services.ResourceNotFoundException;
import com.dave.todos.usersservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractRestController {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllUsers() throws Exception {
        UserDTO user1 = UserDTO.builder()
                .firstName("Name1")
                .build();
        UserDTO user2 = UserDTO.builder()
                .firstName("Name2")
                .build();
        UserDTO user3 = UserDTO.builder()
                .firstName("Name3")
                .build();

        List<UserDTO> users = Arrays.asList(user1, user2, user3);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get(UserController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", is("Name1")));
    }

    @Test
    public void getUserById() throws Exception {
        UserDTO user1 = UserDTO.builder()
                .firstName(FIRST_NAME)
                .build();

        when(userService.getUserById(anyLong())).thenReturn(user1);

        mockMvc.perform(get(UserController.BASE_URL + "/" + ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void createNewUser() throws Exception {
        UserDTO dtoToUpdate = UserDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .address(ADDRESS)
                .postalZip(POSTAL_ZIP)
                .region(REGION)
                .country(COUNTRY)
                .build();

        UserDTO returnDto = UserDTO.builder()
                .firstName(dtoToUpdate.getFirstName())
                .lastName(dtoToUpdate.getLastName())
                .email(dtoToUpdate.getEmail())
                .address(dtoToUpdate.getAddress())
                .postalZip(dtoToUpdate.getPostalZip())
                .region(dtoToUpdate.getRegion())
                .country(dtoToUpdate.getCountry())
                .userUrl(UserController.BASE_URL + "/" + ID)
                .build();

        when(userService.createNewUser(any(UserDTO.class))).thenReturn(returnDto);

        mockMvc.perform(post(UserController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dtoToUpdate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.email", is(EMAIL)))
                .andExpect(jsonPath("$.user_url", is(UserController.BASE_URL + "/" + ID)));
    }


    @Test
    public void updateUser() throws Exception {
        UserDTO dtoToUpdate = UserDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .address(ADDRESS)
                .postalZip(POSTAL_ZIP)
                .region(REGION)
                .country(COUNTRY)
                .build();

        UserDTO returnDto = UserDTO.builder()
                .firstName(dtoToUpdate.getFirstName())
                .lastName(dtoToUpdate.getLastName())
                .email(dtoToUpdate.getEmail())
                .address(dtoToUpdate.getAddress())
                .postalZip(dtoToUpdate.getPostalZip())
                .region(dtoToUpdate.getRegion())
                .country(dtoToUpdate.getCountry())
                .userUrl(UserController.BASE_URL + "/" + ID)
                .build();

        when(userService.saveUserByDto(anyLong(), any(UserDTO.class))).thenReturn(returnDto);

        mockMvc.perform(put(UserController.BASE_URL + "/" + ID).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dtoToUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.email", is(EMAIL)))
                .andExpect(jsonPath("$.user_url", is(UserController.BASE_URL + "/" + ID)));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete(UserController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        when(userService.getUserById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(UserController.BASE_URL + "/" + ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
