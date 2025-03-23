package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("Get all users - success")
    void testGetAllUsers() throws Exception {
        UserDto user = getSampleUser();
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value("john_doe"));
    }

    @Test
    @DisplayName("Get user by ID - success")
    void testGetUserById() throws Exception {
        UserDto user = getSampleUser();
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("john_doe"));
    }

    @Test
    @DisplayName("Update user - success")
    void testUpdateUser() throws Exception {
        UserDto user = getSampleUser();
        when(userService.updateUser(user, 1L)).thenReturn(user);

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("john_doe"));
    }

    @Test
    @DisplayName("Delete user - success")
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("User deleted!"));
    }

    @Test
    @DisplayName("Get users by role - success")
    void testGetUsersByRole() throws Exception {
        UserDto user = getSampleUser();
        when(userService.getUsersByRole(UserRoles.TEAM_LEADER)).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/v1/users/role/TEAM_LEADER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].role").value("TEAM_LEADER"));
    }

    @Test
    @DisplayName("Update user role - success")
    void testUpdateUserRole() throws Exception {
        UserDto user = getSampleUser();
        when(userService.updateUserRole(1L, UserRoles.PROJECT_MANAGER)).thenReturn(user);

        mockMvc.perform(put("/api/v1/users/update-role/1")
                        .param("newRole", "PROJECT_MANAGER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.role").value("TEAM_LEADER")); // Not: role test verisi hâlâ TEAM_LEADER, örnek olarak
    }

    private UserDto getSampleUser() {
        return UserDto.builder()
                .id(1L)
                .username("john_doe")
                .email("john@example.com")
                .password("1234")
                .role(UserRoles.TEAM_LEADER)
                .tasks(Collections.emptyList())
                .build();
    }
}
