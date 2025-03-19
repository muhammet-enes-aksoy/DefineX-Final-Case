package com.example.taskmanagement.dto.comment;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto implements BaseDto {

    private Long id;

    private String text;

    private User author;

    private Task task;
}
