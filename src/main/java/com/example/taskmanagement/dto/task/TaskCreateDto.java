package com.example.taskmanagement.dto.task;

import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskCreateDto {

    private String title;

    private String description;

    private TaskPriority priority;

    private TaskState state;

}
