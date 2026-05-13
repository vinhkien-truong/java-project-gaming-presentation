package com.game.hyf.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hyf.dto.user.LoginRequestDTO;
import com.game.hyf.dto.user.LoginResponseDTO;
import com.game.hyf.dto.user.UserCreateDTO;
import com.game.hyf.dto.user.UserResponseDTO;
import com.game.hyf.dto.user.UserUpdateDTO;
import com.game.hyf.exception.UserNotFoundException;
import com.game.hyf.mapper.UserMapper;
import com.game.hyf.model.RefreshToken;
import com.game.hyf.model.User;
import com.game.hyf.repository.UserRepository;
import com.game.hyf.security.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO create(UserCreateDTO dto) {
        if(repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = mapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDTO(repository.save(user));
    }

 
    public LoginResponseDTO login(LoginRequestDTO dto) {
        //make both exceptions the same to avoid giving hints to the attacker about which one is wrong
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        Map<String, Object> claims = Map.of("userId", user.getId(), "role", user.getRole());
        String token = jwtUtils.generateToken(user.getEmail(), claims);
        System.out.println("Generated Token: " + token);

        RefreshToken refreshToken = refreshTokenService.create(user);
        return new LoginResponseDTO(token, refreshToken.getToken());
    }

    @Transactional
    public void delete(UUID id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        repository.delete(user);
    }

    @Transactional
    public UserResponseDTO update(UUID id, UserUpdateDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (dto.getUsername() != null)
            user.setUsername(dto.getUsername());
        if (dto.getEmail() != null)
            user.setEmail(dto.getEmail());
        if (dto.getPassword() != null)
            user.setPassword(dto.getPassword());
        if (dto.getRole() != null)
            user.setRole(dto.getRole());
        if (dto.getCountry() != null)
            user.setCountry(dto.getCountry());
        return mapper.toDTO(repository.save(user));
    }

      /**
     * Issues a new access token using a valid refresh token.
     * The old refresh token is rotated (replaced with a new one) for security.
     *
     * Throws IllegalArgumentException if the refresh token is invalid or expired.
     */
    public LoginResponseDTO refresh(String refreshTokenValue) {
        // validate() throws if the token doesn't exist in DB or is expired
        RefreshToken refreshToken = refreshTokenService.validate(refreshTokenValue);

        User user = refreshToken.getUser();

        Map<String, Object> claims = Map.of(
                "userId", user.getId(),
                "role", user.getRole().name()
        );

        String newAccessToken = jwtUtils.generateToken(user.getEmail(), claims);

        // Rotate: delete old refresh token, issue a new one
        RefreshToken newRefreshToken = refreshTokenService.rotate(refreshToken);

        return new LoginResponseDTO(newAccessToken, newRefreshToken.getToken());
    }

    /**
     * Logs out the user by deleting their refresh token from the database.
     * Their current access token will keep working until it naturally expires
     * (up to 15 minutes) — that is an accepted trade-off with stateless JWTs.
     */
    public void logout(User user) {
        refreshTokenService.deleteByUser(user);
    }

}
