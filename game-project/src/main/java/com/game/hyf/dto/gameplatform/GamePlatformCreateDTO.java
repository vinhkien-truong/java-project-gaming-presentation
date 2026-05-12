package com.game.hyf.dto.gameplatform;

import java.util.UUID;

import com.game.hyf.model.GameFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamePlatformCreateDTO {
    @NotNull(message = "Game ID is required")
    UUID gameId;

    @NotNull(message = "Platform ID is required")
    UUID platformId;

    @Positive(message = "Price must be positive")
    Double price;

    @NotNull(message = "Format is required")
    GameFormat format;
}
