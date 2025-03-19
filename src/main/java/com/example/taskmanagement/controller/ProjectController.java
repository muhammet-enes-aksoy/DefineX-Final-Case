package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.project.ProjectCreateDto;
import com.example.taskmanagement.dto.project.ProjectDto;
import com.example.taskmanagement.base.RestResponse;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.enums.ProjectStatus;
import com.example.taskmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<RestResponse<List<ProjectDto>>> getAllProjects() {
        return ResponseEntity.ok(RestResponse.of(projectService.getAllProjects()));
    }

    @GetMapping("/{projectId}")
    public  ResponseEntity<RestResponse<ProjectDto>> getProjectById(@PathVariable Long projectId) {
        ProjectDto project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(RestResponse.of(project));
    }

    @PostMapping
    public ResponseEntity<RestResponse<ProjectDto>> createProject(@RequestBody ProjectCreateDto projectCreateDto) {
        return ResponseEntity.ok(RestResponse.of(projectService.createProject(projectCreateDto)));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<RestResponse<ProjectDto>> updateProject(@PathVariable Long projectId,
            @RequestBody ProjectCreateDto projectCreateDto) {
        return ResponseEntity.ok(RestResponse.of(projectService.updateProject(projectId, projectCreateDto)));
    }

    @PostMapping("/{projectId}/members/{userId}")
    public ResponseEntity<RestResponse<ProjectDto>> addTeamMember(
            @PathVariable Long projectId,
            @PathVariable Long userId) {
        return ResponseEntity.ok(RestResponse.of(projectService.addTeamMember(projectId, userId)));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<RestResponse<String>> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok(RestResponse.of("Project deleted!"));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<RestResponse<List<ProjectDto>>> getProjectsByStatus(@PathVariable ProjectStatus projectStatus) {
        return ResponseEntity.ok(RestResponse.of(projectService.getProjectsByStatus(projectStatus)));
    }
    @GetMapping("/members/{projectId}")
    public ResponseEntity<RestResponse<List<UserDto>>> getProjectUsers(@PathVariable Long projectId) {
        return ResponseEntity.ok(RestResponse.of(projectService.getProjectUsers(projectId)));
    }
}
