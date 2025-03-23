package com.example.taskmanagement.ControllerTest;

import com.example.taskmanagement.controller.ProjectController;
import com.example.taskmanagement.dto.project.ProjectCreateDto;
import com.example.taskmanagement.dto.project.ProjectDto;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.enums.ProjectStatus;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.service.ProjectService;
import com.example.taskmanagement.base.RestResponse;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    @DisplayName("Get all projects - success")
    void testGetAllProjects() throws Exception {
        ProjectDto project1 = ProjectDto.builder()
                .id(1L)
                .title("Project 1")
                .description("Description 1")
                .department("IT")
                .status(ProjectStatus.HIGH)
                .build();

        ProjectDto project2 = ProjectDto.builder()
                .id(2L)
                .title("Project 2")
                .description("Description 2")
                .department("HR")
                .status(ProjectStatus.MEDIUM)
                .build();

        when(projectService.getAllProjects()).thenReturn(Arrays.asList(project1, project2));

        mockMvc.perform(get("/api/v1/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].title").value("Project 1"))
                .andExpect(jsonPath("$.data[1].title").value("Project 2"));
    }

    @Test
    @DisplayName("Get project by ID - success")
    void testGetProjectById() throws Exception {
        ProjectDto project = ProjectDto.builder()
                .id(1L)
                .title("Project 1")
                .description("Description 1")
                .department("IT")
                .status(ProjectStatus.HIGH)
                .build();

        when(projectService.getProjectById(1L)).thenReturn(project);

        mockMvc.perform(get("/api/v1/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("Project 1"));
    }

    @Test
    @DisplayName("Create project - success")
    void testCreateProject() throws Exception {
        ProjectCreateDto projectCreateDto = ProjectCreateDto.builder()
                .title("New Project")
                .description("New Description")
                .department("IT")
                .status(ProjectStatus.HIGH)
                .build();

        ProjectDto projectDto = ProjectDto.builder()
                .id(1L)
                .title("New Project")
                .description("New Description")
                .department("IT")
                .status(ProjectStatus.HIGH)
                .build();

        when(projectService.createProject(any(ProjectCreateDto.class))).thenReturn(projectDto);

        mockMvc.perform(post("/api/v1/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectCreateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("New Project"));
    }

    @Test
    @DisplayName("Update project - success")
    void testUpdateProject() throws Exception {
        ProjectCreateDto projectCreateDto = ProjectCreateDto.builder()
                .title("Updated Project")
                .description("Updated Description")
                .department("HR")
                .status(ProjectStatus.MEDIUM)
                .build();

        ProjectDto projectDto = ProjectDto.builder()
                .id(1L)
                .title("Updated Project")
                .description("Updated Description")
                .department("HR")
                .status(ProjectStatus.MEDIUM)
                .build();

        when(projectService.updateProject(eq(1L), any(ProjectCreateDto.class))).thenReturn(projectDto);

        mockMvc.perform(put("/api/v1/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectCreateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("Updated Project"));
    }

    @Test
    @DisplayName("Add team member to project - success")
    void testAddTeamMember() throws Exception {
        ProjectDto projectDto = ProjectDto.builder()
                .id(1L)
                .title("Project with new member")
                .description("Description")
                .department("IT")
                .status(ProjectStatus.HIGH)
                .build();

        when(projectService.addTeamMember(1L, 2L)).thenReturn(projectDto);

        mockMvc.perform(post("/api/v1/projects/1/members/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("Project with new member"));
    }

    @Test
    @DisplayName("Delete project - success")
    void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/api/v1/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Project deleted!"));
    }

    @Test
    @DisplayName("Get project users - success")
    void testGetProjectUsers() throws Exception {
        UserDto user1 = UserDto.builder()
                .id(1L)
                .username("user1")
                .email("user1@example.com")
                .role(UserRoles.TEAM_LEADER)
                .build();

        UserDto user2 = UserDto.builder()
                .id(2L)
                .username("user2")
                .email("user2@example.com")
                .role(UserRoles.TEAM_MEMBER)
                .build();

        when(projectService.getProjectUsers(1L)).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/v1/projects/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value("user1"))
                .andExpect(jsonPath("$.data[1].username").value("user2"));
    }
}