package com.example.taskmanagement.mapper;

import com.example.taskmanagement.base.mapper.BaseMapper;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper extends BaseMapper<Task, TaskDto> {
    TaskMapper MAPPER  = Mappers.getMapper(TaskMapper.class);
}