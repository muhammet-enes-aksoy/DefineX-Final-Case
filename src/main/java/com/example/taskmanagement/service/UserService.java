package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.user.UserCreateDto;
import com.example.taskmanagement.dto.user.UserDto;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.base.service.BaseEntityService;
import com.example.taskmanagement.mapper.UserMapper;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseEntityService<User, UserRepository> {

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public List<UserDto> getAllUsers() {

        return UserMapper.MAPPER.converToDtoList(super.findAll());
    }

    public UserDto getUserById(Long id) throws UserNotFoundException {
        return UserMapper.MAPPER.converToDto(super.findByIdWithControl(id));
    }

    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        User newUser = new User() ;
        newUser.setUsername(userCreateDto.getUsername());
        newUser.setPassword(userCreateDto.getPassword());
        newUser.setEmail(userCreateDto.getEmail());
        newUser.setRole(userCreateDto.getRole());
        return UserMapper.MAPPER.converToDto(super.save(newUser));
    }

    @Transactional
    public UserDto updateUser(UserDto userDto, Long id) throws UserNotFoundException {
       User existingUser = super.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
       updateNonNullFields(existingUser, userDto);
       return UserMapper.MAPPER.converToDto(super.save(existingUser));
    }

    @Transactional
    public void deleteUser(Long id) throws UserNotFoundException {
        User user = super.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        super.delete(user);

    }

    public List<UserDto> getUsersByRole(UserRoles role) {
        List<User> users = super.findAll()
                .stream()
                .filter(user -> user.getRole().equals(role))
                .collect(Collectors.toList());
        return UserMapper.MAPPER.converToDtoList(users);
    }

    private void updateNonNullFields(User existingUser, UserDto userDto) {
        if (userDto.getUsername() != null && !userDto.getUsername().isBlank()) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            existingUser.setPassword(userDto.getPassword());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().isBlank()) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getRole() != null) {
            existingUser.setRole(userDto.getRole());
        }
    }

}
