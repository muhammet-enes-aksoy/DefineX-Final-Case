package com.example.taskmanagement.dto.task;

import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.entity.Project;
import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {

    private Long id;

    private Long projectId;

    private Long userId;

    private String title;

    private String description;

    private TaskPriority priority;

    private TaskState state;

    private UserDto assignedUser;

    private List<CommentDto> comments;

    private List<AttachmentDto> attachments;
}
