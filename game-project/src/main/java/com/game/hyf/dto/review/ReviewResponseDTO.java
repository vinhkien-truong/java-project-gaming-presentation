package com.game.hyf.dto.review;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponseDTO {
        UUID id;
        UUID gamePlatformId;
        UUID userId;
        Integer rating;
        String comment;
}
