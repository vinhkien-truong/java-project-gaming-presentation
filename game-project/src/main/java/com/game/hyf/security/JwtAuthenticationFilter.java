package com.game.hyf.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.game.hyf.model.User;
import com.game.hyf.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Component
@RequiredArgsConstructor
// This filter intercepts incoming HTTP requests to validate JWT tokens and set the authentication context for authorized users.
// It extends OncePerRequestFilter to ensure that the filter is executed once per request.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    /*
        The doFilterInternal method is the core of this filter, where it performs the following steps:
        1. It retrieves the "Authorization" header from the incoming HTTP request.
        2. If the header is missing or does not start with "Bearer ", it simply continues the filter chain without setting any authentication (i.e., it allows unauthenticated requests to pass through).
        3. If the header is present and starts with "Bearer ", it extracts the JWT token from the header.
        4. It validates the token using the jwtUtils.isValid() method. If the token is valid, it proceeds to extract the subject (user identifier) from the token.
        5. If a subject is found and there is no existing authentication in the security context, it retrieves the corresponding user from the database using the UserRepository.
        6. If a user is found, it creates an authentication token (UsernamePasswordAuthenticationToken) with the user's details and authorities, and sets it in the SecurityContextHolder to establish the user's authentication for the current request.
        7. Finally, it continues the filter chain to allow further processing of the request.
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        if (jwtUtils.isValid(token)) {
            String email = jwtUtils.getSubject(token);
            
            // Optimization: If user is already authenticated in this thread, skip DB call
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByEmail(email).orElse(null);

                if (user != null) {
                    var authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            user, 
                            null, 
                            List.of(authority)
                    );

                    // IMPORTANT: Build details from the request
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}