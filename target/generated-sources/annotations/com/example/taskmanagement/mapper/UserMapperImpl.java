package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.attachment.AttachmentDto;
import com.example.taskmanagement.dto.comment.CommentDto;
import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-23T19:27:37+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User converToEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUsername( dto.getUsername() );
        user.setPassword( dto.getPassword() );
        user.setEmail( dto.getEmail() );
        user.setRole( dto.getRole() );
        user.setTasks( taskDtoListToTaskList( dto.getTasks() ) );

        return user;
    }

    @Override
    public UserDto converToDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( entity.getId() );
        userDto.username( entity.getUsername() );
        userDto.password( entity.getPassword() );
        userDto.email( entity.getEmail() );
        userDto.role( entity.getRole() );
        userDto.tasks( taskListToTaskDtoList( entity.getTasks() ) );

        return userDto.build();
    }

    @Override
    public List<UserDto> converToDtoList(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( converToDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> converToEntityList(List<User> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( User user : dtoList ) {
            list.add( user );
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

    protected Task taskDtoToTask(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDto.getId() );
        task.setTitle( taskDto.getTitle() );
        task.setDescription( taskDto.getDescription() );
        task.setPriority( taskDto.getPriority() );
        task.setState( taskDto.getState() );
        task.setReason( taskDto.getReason() );
        task.setComments( commentDtoListToCommentList( taskDto.getComments() ) );
        task.setAttachments( attachmentDtoListToAttachmentList( taskDto.getAttachments() ) );

        return task;
    }

    protected List<Task> taskDtoListToTaskList(List<TaskDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Task> list1 = new ArrayList<Task>( list.size() );
        for ( TaskDto taskDto : list ) {
            list1.add( taskDtoToTask( taskDto ) );
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

    protected TaskDto taskToTaskDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.id( task.getId() );
        taskDto.title( task.getTitle() );
        taskDto.description( task.getDescription() );
        taskDto.reason( task.getReason() );
        taskDto.priority( task.getPriority() );
        taskDto.state( task.getState() );
        taskDto.comments( commentListToCommentDtoList( task.getComments() ) );
        taskDto.attachments( attachmentListToAttachmentDtoList( task.getAttachments() ) );

        return taskDto.build();
    }

    protected List<TaskDto> taskListToTaskDtoList(List<Task> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskDto> list1 = new ArrayList<TaskDto>( list.size() );
        for ( Task task : list ) {
            list1.add( taskToTaskDto( task ) );
        }

        return list1;
    }
}
