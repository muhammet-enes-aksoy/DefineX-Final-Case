package com.example.taskmanagement.dto.user;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.enums.UserRoles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto implements BaseDto {
    private Long id;
    @Schema(description = "Kullanıcı adı", example = "john_doe")
    private String username;

    @Schema(description = "Şifre", example = "password123", required = false)
    private String password;

    @Schema(description = "E-posta adresi", example = "mail@definex.com")
    private String email;

    @Schema(description = "Kullanıcı rolü", example = "PROJECT_MANAGER")
    private UserRoles role;

}

