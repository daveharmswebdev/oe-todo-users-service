package com.dave.todos.usersservice.api.v1.mapper;

import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    private static final Long ID = 1L;
    private static final String FIRST_NAME = "First";
    private static final String LAST_NAME = "Last";
    private static final String EMAIL = "yo@email.com";
    private static final String ADDRESS = "1 Address Road";
    private static final String POSTAL_ZIP = "12345";
    private static final String REGION = "Region";
    private static final String COUNTRY = "Country";

    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void userToUserDto() throws Exception {
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

        // when
        UserDTO userDto = userMapper.userToUserDto(user);

        // then
        assertEquals(FIRST_NAME, userDto.getFirstName());
        assertEquals(LAST_NAME, userDto.getLastName());
        assertEquals(EMAIL, userDto.getEmail());
        assertEquals(ADDRESS, userDto.getAddress());
        assertEquals(POSTAL_ZIP, userDto.getPostalZip());
        assertEquals(REGION, userDto.getRegion());
        assertEquals(COUNTRY, userDto.getCountry());
    }
}
