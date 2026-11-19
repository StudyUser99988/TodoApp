package com.example.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(authorize ->
                http
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/",
                                        "/error",
                                        "/css/**",
                                        "/js/**",
                                        "/favicon.ico")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                        )
                        .oauth2Login(Customizer.withDefaults()));
        return http.build();
    }
}
