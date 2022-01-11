package com.dave.todos.usersservice.api.v2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Dave Harms
 * Date: 1/8/22
 * Time: 3:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LastNameDTO {
    private Long id;
    private String lastName;
}
