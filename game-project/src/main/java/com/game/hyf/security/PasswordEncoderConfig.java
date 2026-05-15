package com.game.hyf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    // This method defines a bean for the PasswordEncoder interface, which uses BCryptPasswordEncoder as the implementation.
    // By defining this bean, we can inject PasswordEncoder into other components (e.g., services) 
    // to handle password hashing and verification consistently across the application.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
