package com.dave.todos.usersservice.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String postalZip;
    private String region;
    private String country;

    @JsonProperty("user_url")
    private String userUrl;
}
