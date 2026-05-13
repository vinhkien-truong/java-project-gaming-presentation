package com.game.hyf.dto.gameplatform;

import java.util.List;
import java.util.UUID;

import com.game.hyf.dto.review.ReviewSummaryDTO;
import com.game.hyf.model.GameFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamePlatformDetailDTO {
    private UUID id;
    private UUID gameId;
    private UUID platformId;
    private String gameName;
    private String platformName;
    private String manufacturer;
    private Double price;
    private GameFormat format;
    private List<ReviewSummaryDTO> reviews;
}
