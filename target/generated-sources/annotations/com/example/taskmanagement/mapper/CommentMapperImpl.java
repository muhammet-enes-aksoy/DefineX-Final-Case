package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T21:05:31+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment converToEntity(CommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( dto.getId() );
        comment.setText( dto.getText() );

        return comment;
    }

    @Override
    public CommentDto converToDto(Comment entity) {
        if ( entity == null ) {
            return null;
        }

        CommentDto.CommentDtoBuilder commentDto = CommentDto.builder();

        commentDto.id( entity.getId() );
        commentDto.text( entity.getText() );

        return commentDto.build();
    }

    @Override
    public List<CommentDto> converToDtoList(List<Comment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommentDto> list = new ArrayList<CommentDto>( entityList.size() );
        for ( Comment comment : entityList ) {
            list.add( converToDto( comment ) );
        }

        return list;
    }

    @Override
    public List<Comment> converToEntityList(List<Comment> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( dtoList.size() );
        for ( Comment comment : dtoList ) {
            list.add( comment );
        }

        return list;
    }
}
