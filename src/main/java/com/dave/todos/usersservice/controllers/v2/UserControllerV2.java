package com.dave.todos.usersservice.controllers.v2;

import com.dave.todos.usersservice.api.v1.model.UserDTO;
import com.dave.todos.usersservice.api.v2.model.LastNameDTO;
import com.dave.todos.usersservice.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: Dave Harms
 * Date: 1/7/22
 * Time: 7:05 AM
 */
@RestController
@RequestMapping(UserControllerV2.BASE_URL)
public class UserControllerV2 {

    public static final String BASE_URL = "/api/v2/users";

    private final UserService userService;

    public UserControllerV2(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> getPageOfUser(
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "lastName", defaultValue = "") String lastName) {

        return userService.getUserPageByLastName(pageSize, page, lastName);

//        return lastName.isEmpty()
//                ? userService.getUserPage(pageSize, page)
//                : userService.getUserPageByLastName(pageSize, page, lastName);
    }

    @GetMapping("/last")
    @ResponseStatus(HttpStatus.OK)
    public List<LastNameDTO> getUserLastNames(@RequestParam(value = "lastName", defaultValue = "") String lastName) {
        return userService.getAllLastNames(lastName);
    }
}
