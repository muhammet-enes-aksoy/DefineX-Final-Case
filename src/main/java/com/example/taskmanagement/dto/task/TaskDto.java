package com.example.taskmanagement.dto.task;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.entity.Project;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskState;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskDto implements BaseDto {
    private Long id;

    private String title;

    private String description;

    private TaskPriority priority;

    private TaskState state;

    private String reason;

    private Project project;

    private User assignee;

    private List<Comment> comments;

    private List<Attachment> attachments;
}
