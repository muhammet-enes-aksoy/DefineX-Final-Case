package com.example.taskmanagement.dto;

import com.example.taskmanagement.enums.UserRoles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
    private UserRoles role;
}
