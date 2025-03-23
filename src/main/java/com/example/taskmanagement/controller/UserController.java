package com.example.taskmanagement.controller;

import com.example.taskmanagement.base.RestResponse;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<List<UserDto>>> getAllUsers() {
        return ResponseEntity.ok(RestResponse.of(userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<UserDto>> getUserById(
            @PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(RestResponse.of(userService.getUserById(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEAM_MEMBERS') or hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<UserDto>> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto userDto) throws UserNotFoundException {
        return ResponseEntity.ok(RestResponse.of(userService.updateUser(userDto, id)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<String>> deleteUser(
            @PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok(RestResponse.of("User deleted!"));
    }

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<List<UserDto>>> getUsersByRole(
            @RequestParam UserRoles role) {
        return ResponseEntity.ok(RestResponse.of(userService.getUsersByRole(role)));
    }

    @PutMapping("/update-role/{userId}")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<UserDto>> updateUserRole(
            @PathVariable Long userId,
            @RequestParam UserRoles newRole) {
        return ResponseEntity.ok(RestResponse.of(userService.updateUserRole(userId, newRole)));
    }
}
