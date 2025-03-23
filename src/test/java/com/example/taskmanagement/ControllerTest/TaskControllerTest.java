/*package com.example.taskmanagement.ControllerTest;

import com.example.taskmanagement.controller.TaskController;
import com.example.taskmanagement.dto.task.TaskCreateDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.dto.task.TaskResponse;
import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskState;
import com.example.taskmanagement.service.TaskService;
import com.example.taskmanagement.base.RestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks() {
        // Arrange
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("Test Task");
        taskDto.setDescription("Test Description");
        taskDto.setPriority(TaskPriority.HIGH);
        taskDto.setState(TaskState.BACKLOG);

        when(taskService.getAllTasks()).thenReturn(Collections.singletonList(taskDto));

        // Act
        ResponseEntity<RestResponse<List<TaskResponse>>> response = taskController.getAllTasks();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getData().size());
        assertEquals("Test Task", response.getBody().getData().get(0).getTitle());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void getTaskById_ShouldReturnTask() {
        // Arrange
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("Test Task");
        taskDto.setDescription("Test Description");
        taskDto.setPriority(TaskPriority.HIGH);
        taskDto.setState(TaskState.BACKLOG);

        when(taskService.getTaskById(1L)).thenReturn(taskDto);

        // Act
        ResponseEntity<RestResponse<TaskResponse>> response = taskController.getTaskById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Task", response.getBody().getData().getTitle());
        verify(taskService, times(1)).getTaskById(1L);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() {
        // Arrange
        TaskCreateDto taskCreateDto = new TaskCreateDto();
        taskCreateDto.setTitle("New Task");
        taskCreateDto.setDescription("New Description");
        taskCreateDto.setPriority(TaskPriority.MEDIUM);
        taskCreateDto.setState(TaskState.BACKLOG);

        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("New Task");
        taskDto.setDescription("New Description");
        taskDto.setPriority(TaskPriority.MEDIUM);
        taskDto.setState(TaskState.BACKLOG);

        when(taskService.createTask(1L, taskCreateDto)).thenReturn(taskDto);

        // Act
        ResponseEntity<RestResponse<TaskResponse>> response = taskController.createTask(1L, taskCreateDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("New Task", response.getBody().getData().getTitle());
        verify(taskService, times(1)).createTask(1L, taskCreateDto);
    }

    @Test
    void deleteTask_ShouldReturnSuccessMessage() {
        // Arrange
        doNothing().when(taskService).deleteTask(1L);

        // Act
        ResponseEntity<RestResponse<String>> response = taskController.deleteTask(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Task deleted!", response.getBody().getData());
        verify(taskService, times(1)).deleteTask(1L);
    }

    @Test
    void assignTaskToUser_ShouldReturnAssignedTask() {
        // Arrange
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("Assigned Task");
        taskDto.setDescription("Assigned Description");
        taskDto.setPriority(TaskPriority.HIGH);
        taskDto.setState(TaskState.IN_PROGRESS);

        when(taskService.assignTaskToUser(1L, 2L)).thenReturn(taskDto);

        // Act
        ResponseEntity<RestResponse<TaskResponse>> response = taskController.assignTaskToUser(1L, 2L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Assigned Task", response.getBody().getData().getTitle());
        verify(taskService, times(1)).assignTaskToUser(1L, 2L);
    }

    @Test
    void updateTask_ShouldReturnUpdatedTask() {
        // Arrange
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("Updated Task");
        taskDto.setDescription("Updated Description");
        taskDto.setPriority(TaskPriority.HIGH);
        taskDto.setState(TaskState.IN_PROGRESS);

        when(taskService.updateTask(1L, taskDto)).thenReturn(taskDto);

        // Act
        ResponseEntity<TaskResponse> response = taskController.updateTask(1L, taskDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Task", response.getBody().getTitle());
        verify(taskService, times(1)).updateTask(1L, taskDto);
    }

    @Test
    void updateTaskState_ShouldReturnUpdatedTask() {
        // Arrange
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("Task with Updated State");
        taskDto.setDescription("Description");
        taskDto.setPriority(TaskPriority.HIGH);
        taskDto.setState(TaskState.IN_PROGRESS);

        when(taskService.updateTaskStates(1L, TaskState.IN_PROGRESS)).thenReturn(taskDto);

        // Act
        ResponseEntity<TaskResponse> response = taskController.updateTaskState(1L, TaskState.IN_PROGRESS);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Task with Updated State", response.getBody().getTitle());
        verify(taskService, times(1)).updateTaskStates(1L, TaskState.IN_PROGRESS);
    }
}*/