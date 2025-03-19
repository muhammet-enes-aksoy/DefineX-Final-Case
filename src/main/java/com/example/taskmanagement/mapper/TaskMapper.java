package com.example.taskmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper{
    TaskMapper MAPPER  = Mappers.getMapper(TaskMapper.class);
}
