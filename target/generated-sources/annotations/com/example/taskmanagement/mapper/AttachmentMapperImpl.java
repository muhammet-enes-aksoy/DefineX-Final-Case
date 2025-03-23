package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.entity.Attachment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-23T19:27:37+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
public class AttachmentMapperImpl implements AttachmentMapper {

    @Override
    public Attachment converToEntity(AttachmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Attachment attachment = new Attachment();

        attachment.setId( dto.getId() );
        attachment.setFilePath( dto.getFilePath() );
        attachment.setFileName( dto.getFileName() );

        return attachment;
    }

    @Override
    public AttachmentDto converToDto(Attachment entity) {
        if ( entity == null ) {
            return null;
        }

        AttachmentDto.AttachmentDtoBuilder attachmentDto = AttachmentDto.builder();

        attachmentDto.id( entity.getId() );
        attachmentDto.filePath( entity.getFilePath() );
        attachmentDto.fileName( entity.getFileName() );

        return attachmentDto.build();
    }

    @Override
    public List<AttachmentDto> converToDtoList(List<Attachment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AttachmentDto> list = new ArrayList<AttachmentDto>( entityList.size() );
        for ( Attachment attachment : entityList ) {
            list.add( converToDto( attachment ) );
        }

        return list;
    }

    @Override
    public List<Attachment> converToEntityList(List<Attachment> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Attachment> list = new ArrayList<Attachment>( dtoList.size() );
        for ( Attachment attachment : dtoList ) {
            list.add( attachment );
        }

        return list;
    }
}
