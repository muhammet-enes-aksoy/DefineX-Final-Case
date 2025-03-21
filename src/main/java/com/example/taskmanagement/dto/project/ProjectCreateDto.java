package com.example.taskmanagement.dto.project;

import com.example.taskmanagement.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectCreateDto {
    private String title;

    private String description;

    private String department;

    private ProjectStatus status;

}
