package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.project.ProjectDto;
import com.example.taskmanagement.entity.Project;
import com.example.taskmanagement.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-20T00:11:11+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public Project converToEntity(ProjectDto dto) {
        if ( dto == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( dto.getId() );
        project.setTitle( dto.getTitle() );
        project.setDescription( dto.getDescription() );
        project.setDepartment( dto.getDepartment() );
        project.setStatus( dto.getStatus() );
        List<User> list = dto.getTeamMembers();
        if ( list != null ) {
            project.setTeamMembers( new ArrayList<User>( list ) );
        }

        return project;
    }

    @Override
    public ProjectDto converToDto(Project entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectDto.ProjectDtoBuilder projectDto = ProjectDto.builder();

        projectDto.id( entity.getId() );
        projectDto.title( entity.getTitle() );
        projectDto.description( entity.getDescription() );
        projectDto.department( entity.getDepartment() );
        projectDto.status( entity.getStatus() );
        List<User> list = entity.getTeamMembers();
        if ( list != null ) {
            projectDto.teamMembers( new ArrayList<User>( list ) );
        }

        return projectDto.build();
    }

    @Override
    public List<ProjectDto> converToDtoList(List<Project> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProjectDto> list = new ArrayList<ProjectDto>( entityList.size() );
        for ( Project project : entityList ) {
            list.add( converToDto( project ) );
        }

        return list;
    }

    @Override
    public List<Project> converToEntityList(List<Project> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Project> list = new ArrayList<Project>( dtoList.size() );
        for ( Project project : dtoList ) {
            list.add( project );
        }

        return list;
    }
}
