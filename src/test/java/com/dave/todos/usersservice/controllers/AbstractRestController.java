package com.dave.todos.usersservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestController {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "First";
    public static final String LAST_NAME = "Last";
    public static final String EMAIL = "yo@email.com";
    public static final String ADDRESS = "1 Address Road";
    public static final String POSTAL_ZIP = "12345";
    public static final String REGION = "Region";
    public static final String COUNTRY = "Country";

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
