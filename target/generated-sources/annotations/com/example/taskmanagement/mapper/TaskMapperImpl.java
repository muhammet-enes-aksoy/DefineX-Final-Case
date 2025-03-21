package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.dto.task.TaskResponse;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.entity.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T21:05:31+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task converToEntity(TaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( dto.getId() );
        task.setTitle( dto.getTitle() );
        task.setDescription( dto.getDescription() );
        task.setPriority( dto.getPriority() );
        task.setState( dto.getState() );
        task.setReason( dto.getReason() );
        task.setComments( commentDtoListToCommentList( dto.getComments() ) );
        task.setAttachments( attachmentDtoListToAttachmentList( dto.getAttachments() ) );

        return task;
    }

    @Override
    public TaskDto converToDto(Task entity) {
        if ( entity == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.id( entity.getId() );
        taskDto.title( entity.getTitle() );
        taskDto.description( entity.getDescription() );
        taskDto.reason( entity.getReason() );
        taskDto.priority( entity.getPriority() );
        taskDto.state( entity.getState() );
        taskDto.comments( commentListToCommentDtoList( entity.getComments() ) );
        taskDto.attachments( attachmentListToAttachmentDtoList( entity.getAttachments() ) );

        return taskDto.build();
    }

    @Override
    public List<TaskDto> converToDtoList(List<Task> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TaskDto> list = new ArrayList<TaskDto>( entityList.size() );
        for ( Task task : entityList ) {
            list.add( converToDto( task ) );
        }

        return list;
    }

    @Override
    public List<Task> converToEntityList(List<Task> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( dtoList.size() );
        for ( Task task : dtoList ) {
            list.add( task );
        }

        return list;
    }

    @Override
    public TaskResponse convertToResponse(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskResponse.TaskResponseBuilder taskResponse = TaskResponse.builder();

        taskResponse.id( taskDto.getId() );
        taskResponse.projectId( taskDto.getProjectId() );
        taskResponse.userId( taskDto.getUserId() );
        taskResponse.title( taskDto.getTitle() );
        taskResponse.description( taskDto.getDescription() );
        taskResponse.priority( taskDto.getPriority() );
        taskResponse.state( taskDto.getState() );
        List<CommentDto> list = taskDto.getComments();
        if ( list != null ) {
            taskResponse.comments( new ArrayList<CommentDto>( list ) );
        }
        List<AttachmentDto> list1 = taskDto.getAttachments();
        if ( list1 != null ) {
            taskResponse.attachments( new ArrayList<AttachmentDto>( list1 ) );
        }

        return taskResponse.build();
    }

    @Override
    public List<TaskResponse> convertToResponseList(List<TaskDto> taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        List<TaskResponse> list = new ArrayList<TaskResponse>( taskDto.size() );
        for ( TaskDto taskDto1 : taskDto ) {
            list.add( convertToResponse( taskDto1 ) );
        }

        return list;
    }

    protected Comment commentDtoToComment(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentDto.getId() );
        comment.setText( commentDto.getText() );

        return comment;
    }

    protected List<Comment> commentDtoListToCommentList(List<CommentDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Comment> list1 = new ArrayList<Comment>( list.size() );
        for ( CommentDto commentDto : list ) {
            list1.add( commentDtoToComment( commentDto ) );
        }

        return list1;
    }

    protected Attachment attachmentDtoToAttachment(AttachmentDto attachmentDto) {
        if ( attachmentDto == null ) {
            return null;
        }

        Attachment attachment = new Attachment();

        attachment.setId( attachmentDto.getId() );
        attachment.setFilePath( attachmentDto.getFilePath() );
        attachment.setFileName( attachmentDto.getFileName() );

        return attachment;
    }

    protected List<Attachment> attachmentDtoListToAttachmentList(List<AttachmentDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Attachment> list1 = new ArrayList<Attachment>( list.size() );
        for ( AttachmentDto attachmentDto : list ) {
            list1.add( attachmentDtoToAttachment( attachmentDto ) );
        }

        return list1;
    }

    protected CommentDto commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto.CommentDtoBuilder commentDto = CommentDto.builder();

        commentDto.id( comment.getId() );
        commentDto.text( comment.getText() );

        return commentDto.build();
    }

    protected List<CommentDto> commentListToCommentDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentDto> list1 = new ArrayList<CommentDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToCommentDto( comment ) );
        }

        return list1;
    }

    protected AttachmentDto attachmentToAttachmentDto(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }

        AttachmentDto.AttachmentDtoBuilder attachmentDto = AttachmentDto.builder();

        attachmentDto.id( attachment.getId() );
        attachmentDto.filePath( attachment.getFilePath() );
        attachmentDto.fileName( attachment.getFileName() );

        return attachmentDto.build();
    }

    protected List<AttachmentDto> attachmentListToAttachmentDtoList(List<Attachment> list) {
        if ( list == null ) {
            return null;
        }

        List<AttachmentDto> list1 = new ArrayList<AttachmentDto>( list.size() );
        for ( Attachment attachment : list ) {
            list1.add( attachmentToAttachmentDto( attachment ) );
        }

        return list1;
    }
}
