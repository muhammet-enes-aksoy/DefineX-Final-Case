package com.example.taskmanagement.dto.user;

import com.example.taskmanagement.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateDto {
    private String username;
    private String password;
    private String email;
    private UserRoles role;

}