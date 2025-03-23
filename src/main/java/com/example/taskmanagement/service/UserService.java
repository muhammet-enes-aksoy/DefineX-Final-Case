package com.example.taskmanagement.service;

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

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository, UserRepository userRepository1) {
        super(userRepository);
        this.userRepository = userRepository1;
    }
    public List<UserDto> getAllUsers() {

        return UserMapper.MAPPER.converToDtoList(super.findAll());
    }
    public UserDto getUserById(Long id) throws UserNotFoundException {
        return UserMapper.MAPPER.converToDto(super.findByIdWithControl(id));
    }
    public UserDto findUserByUsername(String username) throws UserNotFoundException {
        return UserMapper.MAPPER.converToDto(userRepository.findByUsername(username).orElseThrow());
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
    public UserDto updateUserRole(Long id, UserRoles newRole) throws UserNotFoundException {
        User user = super.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(newRole);
        super.save(user);
        return UserMapper.MAPPER.converToDto(user);
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
