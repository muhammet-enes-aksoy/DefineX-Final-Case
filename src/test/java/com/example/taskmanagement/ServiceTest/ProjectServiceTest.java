package com.example.taskmanagement.ServiceTest;

import com.example.taskmanagement.dto.project.ProjectCreateDto;
import com.example.taskmanagement.dto.project.ProjectDto;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.Project;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.ProjectStatus;
import com.example.taskmanagement.exception.ProjectNotFoundException;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.mapper.ProjectMapper;
import com.example.taskmanagement.mapper.UserMapper;
import com.example.taskmanagement.repository.ProjectRepository;
import com.example.taskmanagement.service.ProjectService;
import com.example.taskmanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get all projects - success")
    void testGetAllProjects() {
        // Arrange
        Project project1 = new Project();
        project1.setId(1L);
        project1.setTitle("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setTitle("Project 2");

        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));

        // Act
        List<ProjectDto> result = projectService.getAllProjects();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getTitle());
        assertEquals("Project 2", result.get(1).getTitle());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Get project by ID - success")
    void testGetProjectById() {
        // Arrange
        Project project = new Project();
        project.setId(1L);
        project.setTitle("Project 1");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        // Act
        ProjectDto result = projectService.getProjectById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Project 1", result.getTitle());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Get project by ID - not found")
    void testGetProjectByIdNotFound() {
        // Arrange
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById(1L));
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Create project - success")
    void testCreateProject() {
        // Arrange
        ProjectCreateDto projectCreateDto = ProjectCreateDto.builder()
                .title("New Project")
                .description("Description")
                .department("IT")
                .status(ProjectStatus.HIGH)
                .build();

        Project project = new Project();
        project.setId(1L);
        project.setTitle("New Project");

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // Act
        ProjectDto result = projectService.createProject(projectCreateDto);

        // Assert
        assertNotNull(result);
        assertEquals("New Project", result.getTitle());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    @DisplayName("Update project - success")
    void testUpdateProject() {
        // Arrange
        Project existingProject = new Project();
        existingProject.setId(1L);
        existingProject.setTitle("Old Project");

        ProjectCreateDto projectCreateDto = ProjectCreateDto.builder()
                .title("Updated Project")
                .description("Updated Description")
                .department("HR")
                .status(ProjectStatus.MEDIUM)
                .build();

        when(projectRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(any(Project.class))).thenReturn(existingProject);

        // Act
        ProjectDto result = projectService.updateProject(1L, projectCreateDto);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Project", result.getTitle());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    @DisplayName("Update project - not found")
    void testUpdateProjectNotFound() {
        // Arrange
        ProjectCreateDto projectCreateDto = ProjectCreateDto.builder()
                .title("Updated Project")
                .build();

        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProjectNotFoundException.class, () -> projectService.updateProject(1L, projectCreateDto));
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Add team member to project - success")
    void testAddTeamMember() {
        // Arrange
        Project project = new Project();
        project.setId(1L);
        project.setTitle("Project 1");

        User user = new User();
        user.setId(2L);
        user.setUsername("user1");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userService.findById(2L)).thenReturn(Optional.of(user));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // Act
        ProjectDto result = projectService.addTeamMember(1L, 2L);

        // Assert
        assertNotNull(result);
        assertEquals("Project 1", result.getTitle());
        verify(projectRepository, times(1)).findById(1L);
        verify(userService, times(1)).findById(2L);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    @DisplayName("Add team member to project - user already in team")
    void testAddTeamMemberAlreadyInTeam() {
        // Arrange
        Project project = new Project();
        project.setId(1L);
        project.setTitle("Project 1");

        User user = new User();
        user.setId(2L);
        user.setUsername("user1");

        project.getTeamMembers().add(user);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userService.findById(2L)).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> projectService.addTeamMember(1L, 2L));
        verify(projectRepository, times(1)).findById(1L);
        verify(userService, times(1)).findById(2L);
    }

    @Test
    @DisplayName("Delete project - success")
    void testDeleteProject() {
        // Arrange
        Project project = new Project();
        project.setId(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        // Act
        projectService.deleteProject(1L);

        // Assert
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    @DisplayName("Delete project - not found")
    void testDeleteProjectNotFound() {
        // Arrange
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProjectNotFoundException.class, () -> projectService.deleteProject(1L));
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Get projects by status - success")
    void testGetProjectsByStatus() {
        // Arrange
        Project project1 = new Project();
        project1.setId(1L);
        project1.setTitle("Project 1");
        project1.setStatus(ProjectStatus.HIGH);

        Project project2 = new Project();
        project2.setId(2L);
        project2.setTitle("Project 2");
        project2.setStatus(ProjectStatus.HIGH);

        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));

        // Act
        List<ProjectDto> result = projectService.getProjectsByStatus(ProjectStatus.HIGH);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getTitle());
        assertEquals("Project 2", result.get(1).getTitle());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Get project users - success")
    void testGetProjectUsers() {
        // Arrange
        Project project = new Project();
        project.setId(1L);

        User user1 = new User();
        user1.setId(2L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(3L);
        user2.setUsername("user2");

        project.getTeamMembers().addAll(Arrays.asList(user1, user2));

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        // Act
        List<UserDto> result = projectService.getProjectUsers(1L);

        // Assert
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
        verify(projectRepository, times(1)).findById(1L);
    }
}