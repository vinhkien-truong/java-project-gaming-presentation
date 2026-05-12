package com.game.hyf.dto.review;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewCreateDTO {
    @NotNull(message = "Game platform ID is required")
    UUID gamePlatformId;

    @NotNull(message = "User ID is required")
    UUID userId;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating cannot exceed 5")
    Integer rating;

    @Size(max = 1000)
    String comment;
}
