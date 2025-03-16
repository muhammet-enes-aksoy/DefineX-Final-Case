package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.UserDto;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.mapper.UserMapper;
import com.example.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    UserService (UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        List<User> activeUsers = userRepository.findAll()
                .stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
        return userMapper.toUserDtoList(activeUsers);
    }

    public UserDto getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .filter(User::isActive)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return userMapper.toUserDto(user);
    }


    public UserDto createUser(UserDto userDto) {
        User newUser = userMapper.toUser(userDto);
        newUser.setActive(true);
        newUser.setCreatedAt(new Date().getTime());
        User savedUser = userRepository.save(newUser);
        return userMapper.toUserDto(savedUser);
    }

    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {

        User existingUser = userRepository.findById(userDto.getId())
                .filter(User::isActive)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userDto.getId()));

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setRole(userDto.getRole().name());
        existingUser.setUpdatedAt(new Date().getTime());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toUserDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .filter(User::isActive)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
       user.setActive(false);
    }

    public List<UserDto> getUsersByRole(UserRoles role) {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.isActive() && user.getRole().equals(role.name())) // Aktif ve rolü eşleşen kullanıcıları getir
                .collect(Collectors.toList());
        return userMapper.toUserDtoList(users);
    }
}
