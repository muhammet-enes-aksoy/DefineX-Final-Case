package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.UserDto;
import com.example.taskmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper MAPPER  = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);
}
