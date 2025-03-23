package com.example.taskmanagement.service;

import com.example.taskmanagement.base.service.BaseEntityService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends BaseEntityService<Project, ProjectRepository> {

    private final UserService userService;

    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        super(projectRepository);
        this.userService = userService;
    }

    public List<ProjectDto> getAllProjects() {
        return ProjectMapper.MAPPER.converToDtoList(super.findAll());
    }

    public ProjectDto getProjectById(Long projectId) {
        return ProjectMapper.MAPPER.converToDto(super.findByIdWithControl(projectId));
    }
    @Transactional
    public ProjectDto createProject(ProjectCreateDto projectCreateDto) {
        Project project = new Project();
        project.setTitle(projectCreateDto.getTitle());
        project.setDescription(projectCreateDto.getDescription());
        project.setDepartment(projectCreateDto.getDepartment());
        project.setStatus(projectCreateDto.getStatus());
        return ProjectMapper.MAPPER.converToDto(super.save(project));
    }
    @Transactional
    public ProjectDto updateProject(Long projectId, ProjectCreateDto projectCreateDto) {
        Project existingProject = super.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found!"));

        existingProject.setTitle(projectCreateDto.getTitle());
        existingProject.setDescription(projectCreateDto.getDescription());
        existingProject.setDepartment(projectCreateDto.getDepartment());
        existingProject.setStatus(projectCreateDto.getStatus());

        return ProjectMapper.MAPPER.converToDto(super.save(existingProject));
    }
    public ProjectDto addTeamMember(Long projectId, Long userId) {
        Project project = super.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found!"));
        User user = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        boolean isUserAlreadyInTeam = project.getTeamMembers().stream()
                .anyMatch(teamMember -> teamMember.getId().equals(userId));

        if (isUserAlreadyInTeam) {
            throw new IllegalArgumentException("User is already a team member in this project.");
        }
        project.getTeamMembers().add(user);
        return ProjectMapper.MAPPER.converToDto(super.save(project));
    }
    @Transactional
    public void deleteProject(Long projectId) {
        Project project = super.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found!"));
        super.delete(project);
    }
    public List<ProjectDto> getProjectsByStatus(ProjectStatus projectStatus) {
        List<Project> projects = super.findAll()
                .stream()
                .filter(project -> project.getStatus().equals(projectStatus))
                .collect(Collectors.toList());
        return ProjectMapper.MAPPER.converToDtoList(projects);
    }
    public List<UserDto> getProjectUsers(Long projectId) {
        Project project = super.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found!"));
        List<User> users = project.getTeamMembers();
        return UserMapper.MAPPER.converToDtoList(users);
    }
}
