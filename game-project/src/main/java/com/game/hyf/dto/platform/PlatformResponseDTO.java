package com.game.hyf.dto.platform;

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
public class PlatformResponseDTO {
    UUID id;
    String name;
    String manufacturer;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
