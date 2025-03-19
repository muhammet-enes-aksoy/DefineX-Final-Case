package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.project.ProjectDto;
import com.example.taskmanagement.dto.task.TaskCreateDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.entity.*;
import com.example.taskmanagement.enums.TaskState;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.exception.InvalidTaskStateException;
import com.example.taskmanagement.exception.ProjectNotFoundException;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.mapper.ProjectMapper;
import com.example.taskmanagement.mapper.TaskMapper;
import com.example.taskmanagement.repository.ProjectRepository;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.base.service.BaseEntityService;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService extends BaseEntityService<Task, TaskRepository> {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        super(taskRepository);
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<TaskDto> getAllTasks() {
        return TaskMapper.MAPPER.converToDtoList(super.findAll());
    }

    public TaskDto getTaskById(Long id) throws TaskNotFoundException {
        return TaskMapper.MAPPER.converToDto(super.findByIdWithControl(id));
    }

    @Transactional
    public TaskDto createTask(Long projectId, TaskCreateDto taskCreateDto) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found!"));

        Task task = new Task();
        task.setPriority(taskCreateDto.getPriority());
        task.setTitle(taskCreateDto.getTitle());
        task.setDescription(taskCreateDto.getDescription());
        task.setState(taskCreateDto.getState());
        project.getTasks().add(task);
        projectRepository.save(project);
        ProjectDto projectDto =  ProjectMapper.MAPPER.converToDto(project);
        projectDto.setTasks(project.getTasks());
        return TaskMapper.MAPPER.converToDto(super.save(task));
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
    }
    @Transactional
    public TaskDto assignTaskToUser(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException {
        Task task = super.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getRole() != UserRoles.TEAM_MEMBER) {
            throw new IllegalArgumentException("Only TEAM_MEMBER role can be assigned to a task");
        }

        task.setAssignee(user);
        return TaskMapper.MAPPER.converToDto(super.save(task));
    }

    public List<Comment> getCommentsByTaskId(Long taskId) throws TaskNotFoundException {
        Task task = super.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return task.getComments();
    }

    public List<Attachment> getAttachmentsByTaskId(Long taskId) throws TaskNotFoundException {
        Task task = super.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return task.getAttachments();
    }
}