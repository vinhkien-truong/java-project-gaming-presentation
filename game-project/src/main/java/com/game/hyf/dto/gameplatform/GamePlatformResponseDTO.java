package com.game.hyf.dto.gameplatform;

import java.util.UUID;

import com.game.hyf.model.GameFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamePlatformResponseDTO {
        UUID id;
        UUID gameId;
        UUID platformId;
        Double price;
        GameFormat format;
}
