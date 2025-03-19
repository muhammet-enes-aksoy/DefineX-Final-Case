package com.example.taskmanagement.mapper;

import com.example.taskmanagement.base.mapper.BaseMapper;
import com.example.taskmanagement.dto.project.ProjectDto;
import com.example.taskmanagement.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper extends BaseMapper<Project, ProjectDto> {
    ProjectMapper MAPPER  = Mappers.getMapper(ProjectMapper.class);
}
