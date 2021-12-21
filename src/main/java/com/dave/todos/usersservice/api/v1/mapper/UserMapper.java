package com.dave.todos.usersservice.api.v1.mapper;


import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userUrl", ignore = true)
    UserDTO userToUserDto(User user);

    @Mapping(target = "id", ignore = true)
    User userDtoToUser(UserDTO userDto);
}
