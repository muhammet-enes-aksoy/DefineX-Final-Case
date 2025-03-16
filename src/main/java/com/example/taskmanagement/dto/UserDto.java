package com.example.taskmanagement.dto;

import com.example.taskmanagement.enums.UserRoles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRoles role;
}
