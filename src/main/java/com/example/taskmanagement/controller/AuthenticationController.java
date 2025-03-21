package com.example.taskmanagement.controller;

import com.example.taskmanagement.base.RestResponse;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.security.request.AuthenticationRequest;
import com.example.taskmanagement.security.request.RegisterRequest;
import com.example.taskmanagement.security.response.AuthenticationResponse;
import com.example.taskmanagement.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<RestResponse<UserDto>> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(RestResponse.of(authService.registerTeamMember(request)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<RestResponse<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(RestResponse.of(authService.authenticate(request)));
    }
}
