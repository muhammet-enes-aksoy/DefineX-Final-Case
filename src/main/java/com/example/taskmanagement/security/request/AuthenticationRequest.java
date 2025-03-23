package com.example.taskmanagement.security.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "Username cannot be blank.")
    @Length(min = 3,max = 25,message = "Username must be between 3-25 characters.")
    @Schema(description = "Username", example = "enes")
    private  String username;

    @NotBlank(message = "Password cannot be blank.")
    @Length(min = 3,max = 25,message = "Password must be between 3-25 characters.")
    @Schema(description = "Password", example = "123", required = false)
    private String password;
}
