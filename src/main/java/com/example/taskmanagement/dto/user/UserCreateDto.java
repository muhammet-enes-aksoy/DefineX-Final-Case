package com.example.taskmanagement.dto.user;

import com.example.taskmanagement.enums.UserRoles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateDto {
    private String username;
    private String password;
    private String email;
    private UserRoles role;

}