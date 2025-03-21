package com.example.taskmanagement.service;

import com.example.taskmanagement.base.service.BaseEntityService;
import com.example.taskmanagement.dto.comment.CommentCreateDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.comment.CommentUpdateDto;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.exception.CommentNotFoundException;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.mapper.CommentMapper;
import com.example.taskmanagement.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService  extends BaseEntityService<Comment, CommentRepository> {

    private final TaskService taskService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, TaskService taskService, UserService userService) {
        super(commentRepository);
        this.taskService = taskService;
        this.userService = userService;
    }

    @Transactional
    public CommentDto createComment(Long taskId, Long userId, CommentCreateDto commentCreateDto) {
        Task task = taskService.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        User user = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Comment comment = new Comment();
        comment.setText(commentCreateDto.getText());
        comment.setAuthor(user);
        comment.setTask(task);

        return CommentMapper.MAPPER.converToDto(super.save(comment));
    }

    @Transactional
    public CommentDto updateComment(Long commentId, CommentUpdateDto commentUpdateDto) {
        Comment comment = super.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        comment.setText(commentUpdateDto.getNewText());
        return CommentMapper.MAPPER.converToDto(super.save(comment));
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = super.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        super.delete(comment);
    }
}
