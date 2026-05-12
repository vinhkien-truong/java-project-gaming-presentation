package com.game.hyf.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.game.hyf.dto.user.UserCreateDTO;
import com.game.hyf.dto.user.UserResponseDTO;
import com.game.hyf.dto.user.UserUpdateDTO;
import com.game.hyf.exception.UserNotFoundException;
import com.game.hyf.mapper.UserMapper;
import com.game.hyf.model.User;
import com.game.hyf.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    // private final PasswordEncoder passwordEncoder;

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
        User user = mapper.toEntity(dto);
        // Hash the password before saving
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDTO(repository.save(user));
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

}
