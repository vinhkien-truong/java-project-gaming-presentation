package com.game.hyf.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    UUID id;
    String username;
    String email;
    String role;
    String country;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
