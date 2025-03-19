package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.task.TaskCreateDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.base.RestResponse;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<RestResponse<List<TaskDto>>> getAllTasks() {
        return ResponseEntity.ok(RestResponse.of(taskService.getAllTasks()));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<RestResponse<TaskDto>> getTaskById(@PathVariable Long taskId) {
        return ResponseEntity.ok(RestResponse.of(taskService.getTaskById(taskId)));
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<RestResponse<TaskDto>> createTask(@PathVariable Long projectId, @RequestBody TaskCreateDto taskCreateDto) {
        return ResponseEntity.ok(RestResponse.of(taskService.createTask(projectId, taskCreateDto)));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<RestResponse<String>> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok(RestResponse.of("Task deleted!"));
    }

    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<RestResponse<TaskDto>> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        return ResponseEntity.ok(RestResponse.of(taskService.assignTaskToUser(taskId, userId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDto));
    }

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<RestResponse<List<Comment>>> getCommentsByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(RestResponse.of(taskService.getCommentsByTaskId(taskId)));
    }

    @GetMapping("/{taskId}/attachments")
    public ResponseEntity<RestResponse<List<Attachment>>> getAttachmentsByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(RestResponse.of(taskService.getAttachmentsByTaskId(taskId)));
    }
}
