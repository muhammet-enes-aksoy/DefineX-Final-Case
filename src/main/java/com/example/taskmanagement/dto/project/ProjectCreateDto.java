package com.example.taskmanagement.dto.project;

import com.example.taskmanagement.enums.ProjectStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectCreateDto {
    private String title;

    private String description;

    private String department;

    private ProjectStatus status;

}
