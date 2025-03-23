package com.example.taskmanagement.security;

import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.UserRoles;
import com.example.taskmanagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserInitializer(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userService.getAllUsers().isEmpty()) {
            User admin = new User();
            admin.setUsername("enes");
            admin.setPassword(passwordEncoder.encode("123"));
            admin.setEmail("enes@definex.com");
            admin.setRole(UserRoles.PROJECT_MANAGER);

            userService.save(admin);
            System.out.println("Default PROJECT_MANAGER user created: enes/123");
        }
    }
}
