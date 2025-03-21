package com.example.taskmanagement.security.service;

import com.example.taskmanagement.base.entity.BaseAdditionalFields;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.mapper.UserMapper;
import com.example.taskmanagement.repository.UserRepository;
import com.example.taskmanagement.security.request.AuthenticationRequest;
import com.example.taskmanagement.security.request.RegisterRequest;
import com.example.taskmanagement.security.response.AuthenticationResponse;
import com.example.taskmanagement.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public UserDto registerTeamMember(RegisterRequest registerRequest){
        User user = createValidUser(registerRequest);
        user.setRole(UserRoles.TEAM_MEMBER);
        return UserMapper.MAPPER.converToDto(this.userService.save(user));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user =  userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .user(UserMapper.MAPPER.converToDto(user))
                .token(jwtToken)
                .build();
    }
    public User createValidUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        BaseAdditionalFields baseAdditionalFields = new BaseAdditionalFields();
        baseAdditionalFields.setCreateDate(LocalDateTime.now());
        baseAdditionalFields.setActive(true);
        user.setBaseAdditionalFields(baseAdditionalFields);
        return user;
    }


}
