package com.example.taskmanagement.mapper;

import com.example.taskmanagement.base.mapper.BaseMapper;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper extends BaseMapper<Comment, CommentDto> {
    CommentMapper MAPPER  = Mappers.getMapper(CommentMapper.class);
}
