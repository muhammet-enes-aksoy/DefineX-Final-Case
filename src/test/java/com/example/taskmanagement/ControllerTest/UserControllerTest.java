package com.example.taskmanagement.ControllerTest;

import com.example.taskmanagement.controller.UserController;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.exception.UserNotFoundException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("Get all users - success")
    void testGetAllUsers() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("enes_aksoy")
                .email("enes@definex.com")
                .password("123")
                .role(UserRoles.TEAM_LEADER)
                .build();

        when(userService.getAllUsers()).thenReturn(List.of(userDto));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value("enes_aksoy"));
    }

    @Test
    @DisplayName("Get user by ID - success")
    void testGetUserById() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("enes_aksoy")
                .email("enes@definex.com")
                .password("123")
                .role(UserRoles.TEAM_LEADER)
                .build();

        when(userService.getUserById(1L)).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("enes_aksoy"));
    }

    @Test
    @DisplayName("Update user - success")
    void testUpdateUser() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("enes_aksoy")
                .email("enes@definex.com")
                .password("123")
                .role(UserRoles.TEAM_LEADER)
                .build();

        when(userService.updateUser(any(UserDto.class), eq(1L))).thenReturn(userDto);

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("enes_aksoy"));
    }

    @Test
    @DisplayName("Delete user - success")
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("User deleted!"));
    }


    @Test
    @DisplayName("Update user role - success")
    void testUpdateUserRole() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("enes_aksoy")
                .email("enes@definex.com")
                .password("123")
                .role(UserRoles.PROJECT_MANAGER)
                .build();

        when(userService.updateUserRole(1L, UserRoles.PROJECT_MANAGER)).thenReturn(userDto);

        mockMvc.perform(put("/api/v1/users/update-role/1?newRole=PROJECT_MANAGER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.role").value("PROJECT_MANAGER"));
    }
}
