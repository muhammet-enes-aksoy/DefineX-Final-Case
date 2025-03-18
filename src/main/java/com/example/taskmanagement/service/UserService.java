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

    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<User> activeUsers = userRepository.findAll()
                .stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
        return UserMapper.MAPPER.toUserDtoList(activeUsers);
    }

    public UserDto getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .filter(User::isActive)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return UserMapper.MAPPER.toUserDto(user);
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User newUser = UserMapper.MAPPER.toUser(userDto);
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        newUser.setActive(true);
        newUser.setCreatedAt(new Date().getTime());
        User savedUser = userRepository.save(newUser);
        return UserMapper.MAPPER.toUserDto(savedUser);
    }

    @Transactional
    public UserDto updateUser(UserDto userDto, Long id) throws UserNotFoundException {

       User existingUser = userRepository.findById(id)
                .filter(User::isActive)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        existingUser.setUsername(userDto.getUsername());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setRole(userDto.getRole());
        existingUser.setUpdatedAt(new Date().getTime());

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.MAPPER.toUserDto(updatedUser);
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
                .filter(user -> user.isActive() && user.getRole().equals(role)) // Aktif ve rolü eşleşen kullanıcıları getir
                .collect(Collectors.toList());
        return UserMapper.MAPPER.toUserDtoList(users);
    }
}
