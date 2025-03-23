package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.attachment.AttachmentCreateDto;
import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.task.TaskCreateDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.base.RestResponse;
import com.example.taskmanagement.dto.task.TaskResponse;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskState;
import com.example.taskmanagement.mapper.TaskMapper;
import com.example.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @PreAuthorize("hasRole('TEAM_MEMBERS') or hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<List<TaskResponse>>> getAllTasks() {
        return ResponseEntity.ok(RestResponse.of(TaskMapper.MAPPER.convertToResponseList(taskService.getAllTasks())));
    }

    @GetMapping("/{taskId}")
    @PreAuthorize("hasRole('TEAM_MEMBERS') or hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<TaskResponse>> getTaskById(
            @PathVariable Long taskId) {
        return ResponseEntity.ok(RestResponse.of(TaskMapper.MAPPER.convertToResponse(taskService.getTaskById(taskId))));
    }

    @GetMapping("state/{state}")
    @PreAuthorize("hasRole('TEAM_MEMBERS') or hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")    public ResponseEntity<RestResponse<List<TaskDto>>> getTaskByState(
            @RequestParam TaskState taskState) {
        return ResponseEntity.ok(RestResponse.of(taskService.getTaskByState(taskState)));
    }
    @GetMapping("priority/{priority}")
    @PreAuthorize("hasRole('TEAM_MEMBERS') or hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<List<TaskDto>>> getTaskByPriority(
            @RequestParam TaskPriority taskPriority) {
        return ResponseEntity.ok(RestResponse.of(taskService.getTaskByPriority(taskPriority)));
    }

    @PostMapping("/{projectId}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<TaskResponse>> createTask(
            @PathVariable Long projectId,
            @RequestBody TaskCreateDto taskCreateDto) {
        TaskResponse taskResponse = TaskMapper.MAPPER.convertToResponse(taskService.createTask(projectId, taskCreateDto));
        taskResponse.setProjectId(projectId);
        return ResponseEntity.ok(RestResponse.of(taskResponse));
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<String>> deleteTask(
            @PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok(RestResponse.of("Task deleted!"));
    }

    @PostMapping("/{taskId}/assign/{userId}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<TaskResponse>> assignTaskToUser(
            @PathVariable Long taskId,
            @PathVariable Long userId) {
        TaskResponse taskResponse = TaskMapper.MAPPER.convertToResponse(taskService.assignTaskToUser(taskId, userId));
        taskResponse.setUserId(userId);
        return ResponseEntity.ok(RestResponse.of(taskResponse));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(TaskMapper.MAPPER.convertToResponse(taskService.updateTask(id, taskDto)));
    }
    @PutMapping("/taskState/{taskId}")
    @PreAuthorize("hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<TaskResponse> updateTaskState(
            @PathVariable Long taskId,
            @RequestParam TaskState taskState) {
        return ResponseEntity.ok(TaskMapper.MAPPER.convertToResponse(taskService.updateTaskStates(taskId, taskState)));
    }

    @GetMapping("/{taskId}/comments")
    @PreAuthorize("hasRole('TEAM_MEMBERS') or hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<List<CommentDto>>> getCommentsByTaskId(
            @PathVariable Long taskId) {
        return ResponseEntity.ok(RestResponse.of(taskService.getCommentsByTaskId(taskId)));
    }

    @GetMapping("/{taskId}/attachments")
    @PreAuthorize("hasRole('TEAM_MEMBERS') or hasRole('TEAM_LEADER') or hasRole('PROJECT_MANAGER')")
    public ResponseEntity<RestResponse<List<AttachmentDto>>> getAttachmentsByTaskId(
            @PathVariable Long taskId) {
        return ResponseEntity.ok(RestResponse.of(taskService.getAttachmentsByTaskId(taskId)));
    }
}
