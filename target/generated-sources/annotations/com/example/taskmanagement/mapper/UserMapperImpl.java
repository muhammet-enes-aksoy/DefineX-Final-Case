package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-19T19:48:48+0300",
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
}
