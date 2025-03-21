package com.example.taskmanagement.dto.project;

import com.example.taskmanagement.base.dto.BaseDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.dto.user.TeamMemberDto;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto implements BaseDto {

    private Long id;

    private String title;

    private String description;

    private String department;

    private ProjectStatus status;

    private List<TeamMemberDto> teamMembers;

    private List<TaskDto> tasks;

}
