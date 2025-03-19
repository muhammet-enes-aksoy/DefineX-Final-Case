package com.example.taskmanagement.dto.project;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.ProjectStatus;
import lombok.*;

import java.util.List;

@Data
@Builder
public class ProjectDto implements BaseDto {

    private Long id;

    private String title;

    private String description;

    private String department;

    private ProjectStatus status;

    private List<User> teamMembers;

}
