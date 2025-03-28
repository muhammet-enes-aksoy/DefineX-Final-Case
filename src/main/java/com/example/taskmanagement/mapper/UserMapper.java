package com.example.taskmanagement.mapper;

import com.example.taskmanagement.base.mapper.BaseMapper;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDto> {
    UserMapper MAPPER  = Mappers.getMapper(UserMapper.class);
}
