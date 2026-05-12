package com.game.hyf.dto.game;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameUpdateDTO {
        @Size(max = 150, message = "Title cannot exceed 150 characters")
        String title;

        LocalDate releaseDate;

        @Size(max = 1000, message = "Description too long")
        String description;

        @Size(max = 30, message = "Version too long")
        String version;
}
