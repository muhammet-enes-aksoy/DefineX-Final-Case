package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.task.TaskDto;
import com.example.taskmanagement.entity.Attachment;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.entity.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T00:47:38+0300",
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
        task.setProject( dto.getProject() );
        task.setAssignee( dto.getAssignee() );
        List<Comment> list = dto.getComments();
        if ( list != null ) {
            task.setComments( new ArrayList<Comment>( list ) );
        }
        List<Attachment> list1 = dto.getAttachments();
        if ( list1 != null ) {
            task.setAttachments( new ArrayList<Attachment>( list1 ) );
        }

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
        taskDto.priority( entity.getPriority() );
        taskDto.state( entity.getState() );
        taskDto.reason( entity.getReason() );
        taskDto.project( entity.getProject() );
        taskDto.assignee( entity.getAssignee() );
        List<Comment> list = entity.getComments();
        if ( list != null ) {
            taskDto.comments( new ArrayList<Comment>( list ) );
        }
        List<Attachment> list1 = entity.getAttachments();
        if ( list1 != null ) {
            taskDto.attachments( new ArrayList<Attachment>( list1 ) );
        }

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
}
