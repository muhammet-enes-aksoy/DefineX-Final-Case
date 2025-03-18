package com.example.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF'yi devre dışı bırak
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/users/**",  // Kullanıcı işlemlerine erişim izni
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic().disable()
                .formLogin().disable();

        return http.build();
    }
}
