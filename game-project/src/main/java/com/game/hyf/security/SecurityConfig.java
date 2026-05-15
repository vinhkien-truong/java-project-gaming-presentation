package com.game.hyf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /*
        This method configures the security filter chain for the application. It sets up various security settings, including:
        1. Disabling CSRF protection (since we're using JWTs and stateless sessions).
        2. Configuring session management to be stateless (no server-side sessions).
        3. Defining authorization rules for different endpoints:
           - Public access to authentication endpoints and API documentation.
           - Public read-only access to GET endpoints for games, platforms, reviews, and game-platforms.
           - Restricted access to user management endpoints for ADMIN and MODERATOR roles.
           - Restricting DELETE operations to ADMIN role only.
           - Requiring authentication for all other requests (e.g., POST/PATCH/PUT).
        4. Adding the JwtAuthenticationFilter before the UsernamePasswordAuthenticationFilter to ensure that JWT validation occurs before any username/password authentication logic.
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Static Public Endpoints
                        .requestMatchers("/api/v1/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/games/**", "/api/platforms/**", "/api/reviews/**",
                                "/api/game-platforms/**")
                        .permitAll()


                        // 2. Admin and moderator-only endpoints
                        .requestMatchers("/api/users/**").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                        //review are public and allow anyone to read or change but only admin can delete for now
                        //TODO later we can add a rule that allows users to delete their own reviews but not others' reviews, while admins can delete any review. 
                        //TODO user can only update their own reviews, while admins and moderators can update any review.
                        .requestMatchers("/api/reviews/**").permitAll()

                        // 3. Everything else (POST/PATCH) requires the role Admin or Moderator
                        .anyRequest().hasAnyRole("ADMIN","MODERATOR"))

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}