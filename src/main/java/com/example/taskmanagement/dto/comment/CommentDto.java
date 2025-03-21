package com.example.taskmanagement.dto.comment;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto implements BaseDto {

    private Long id;

    private String text;

}
