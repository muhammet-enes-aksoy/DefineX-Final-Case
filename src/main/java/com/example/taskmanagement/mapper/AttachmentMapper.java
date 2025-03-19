package com.example.taskmanagement.mapper;

import com.example.taskmanagement.base.mapper.BaseMapper;
import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttachmentMapper extends BaseMapper<Attachment, AttachmentDto> {
    AttachmentMapper MAPPER  = Mappers.getMapper(AttachmentMapper.class);
}
