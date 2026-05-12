package com.game.hyf.dto.game;

import java.time.LocalDate;
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
public class GameResponseDTO {
        UUID id;
        String title;
        LocalDate releaseDate;
        String description;
        String version;
        LocalDateTime createdAt;
        LocalDateTime updatedA;

}
