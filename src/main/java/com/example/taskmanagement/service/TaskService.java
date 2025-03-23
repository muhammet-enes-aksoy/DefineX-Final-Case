package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.project.ProjectDto;
import com.example.taskmanagement.dto.task.TaskCreateDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.*;
import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskState;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.exception.InvalidTaskStateException;
import com.example.taskmanagement.exception.ProjectNotFoundException;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.mapper.ProjectMapper;
import com.example.taskmanagement.mapper.TaskMapper;
import com.example.taskmanagement.mapper.UserMapper;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.base.service.BaseEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService extends BaseEntityService<Task, TaskRepository> {

    private final UserService userService;
    private final ProjectService projectService;

    public TaskService(TaskRepository taskRepository, UserService userService, ProjectService projectService) {
        super(taskRepository);
        this.userService = userService;
        this.projectService = projectService;
    }

    public List<TaskDto> getAllTasks() {
        return TaskMapper.MAPPER.converToDtoList(super.findAll());
    }

    public TaskDto getTaskById(Long id) throws TaskNotFoundException {
        return TaskMapper.MAPPER.converToDto(super.findByIdWithControl(id));
    }
    public List<TaskDto> getTaskByState(TaskState taskState) throws TaskNotFoundException {
        List<Task> tasks = super.findAll()
                .stream()
                .filter(task -> task.getState().equals(taskState))
                .collect(Collectors.toList());
        return TaskMapper.MAPPER.converToDtoList(tasks);
    }
    public List<TaskDto> getTaskByPriority(TaskPriority taskPriority) throws TaskNotFoundException {
        List<Task> tasks = super.findAll()
                .stream()
                .filter(task -> task.getPriority().equals(taskPriority))
                .collect(Collectors.toList());
        return TaskMapper.MAPPER.converToDtoList(tasks);
    }
    @Transactional
    public TaskDto createTask(Long projectId, TaskCreateDto taskCreateDto) {
        Project project = projectService.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found!"));

        Task task = new Task();
        task.setPriority(taskCreateDto.getPriority());
        task.setTitle(taskCreateDto.getTitle());
        task.setDescription(taskCreateDto.getDescription());
        task.setState(taskCreateDto.getState());
        task.setProject(project);
        super.save(task);
        project.getTasks().add(task);
        projectService.save(project);
        return TaskMapper.MAPPER.converToDto(task);
    }
    @Transactional
    public TaskDto updateTask(Long id, TaskDto taskDto) throws TaskNotFoundException {
        Task task = super.findByIdWithControl(id);

        if (taskDto.getTitle() != null) task.setTitle(taskDto.getTitle());
        if (taskDto.getDescription() != null) task.setDescription(taskDto.getDescription());
        if (taskDto.getPriority() != null) task.setPriority(taskDto.getPriority());
        if (taskDto.getState() != null) updateTaskState(task, taskDto.getState(), taskDto.getReason());

        return TaskMapper.MAPPER.converToDto(super.save(task));
    }
    @Transactional
    public void deleteTask(Long id) throws TaskNotFoundException {
        Task task = super.findByIdWithControl(id);
        super.delete(task);
    }
    @Transactional
    public TaskDto updateTaskStates(Long id, TaskState taskState) throws TaskNotFoundException {
        Task task = super.findByIdWithControl(id);

        if (task.getState() != null) updateTaskState(task, task.getState(), task.getReason());

        return TaskMapper.MAPPER.converToDto(super.save(task));
    }
    private void updateTaskState(Task task, TaskState newState, String reason) {
        if (task.getState() == TaskState.COMPLETED) {
            throw new InvalidTaskStateException("Completed tasks cannot change state.");
        }

        if ((newState == TaskState.BLOCKED || newState == TaskState.CANCELLED) && (reason == null || reason.isBlank())) {
            throw new InvalidTaskStateException("Blocked or Cancelled tasks require a reason.");
        }

        task.setState(newState);
        if (newState == TaskState.BLOCKED || newState == TaskState.CANCELLED) {
            task.setReason(reason);
        }
        super.save(task);
    }
    @Transactional
    public TaskDto assignTaskToUser(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException {
        Task task = super.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (user.getRole() != UserRoles.TEAM_MEMBER) {
            throw new IllegalArgumentException("Only TEAM_MEMBER role can be assigned to a task");
        }
        task.setAssignee(user);
        super.save(task);
        user.getTasks().add(task);
        userService.save(user);
        return TaskMapper.MAPPER.converToDto(task);
    }
    public List<CommentDto> getCommentsByTaskId(Long taskId) throws TaskNotFoundException {
        Task task = super.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        TaskDto taskDto = TaskMapper.MAPPER.converToDto(task);
        return taskDto.getComments();
    }
    public List<AttachmentDto> getAttachmentsByTaskId(Long taskId) throws TaskNotFoundException {
        Task task = super.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        TaskDto taskDto = TaskMapper.MAPPER.converToDto(task);
        return taskDto.getAttachments();
    }
}