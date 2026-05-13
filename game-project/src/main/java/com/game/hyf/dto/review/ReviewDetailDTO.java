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
public class ReviewDetailDTO {
    UUID id;
    UUID gamePlatformId;
    UUID userId;
    private String gameName;
    private String platformName;
    private String manufacturer;
    private String username;
    Integer rating;
    String comment;
}
