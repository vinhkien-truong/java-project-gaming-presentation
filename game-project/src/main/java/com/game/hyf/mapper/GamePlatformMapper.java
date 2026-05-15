package com.game.hyf.mapper;

import org.mapstruct.*;

import com.game.hyf.dto.gameplatform.GamePlatformDetailDTO;
import com.game.hyf.dto.gameplatform.GamePlatformResponseDTO;
import com.game.hyf.dto.review.ReviewSummaryDTO;
import com.game.hyf.model.GamePlatform;
import com.game.hyf.model.Review;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GamePlatformMapper {

    // 1. Basic DTO mapping
    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "platform.id", target = "platformId")
    GamePlatformResponseDTO toDTO(GamePlatform gamePlatform);

    // 2. Detail DTO mapping (The one with the list)
    // MapStruct sees 'List<Review> reviews' in Entity and 'List<ReviewSummaryDTO> reviews' in DTO
    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "platform.id", target = "platformId")
    @Mapping(source = "game.title", target = "gameName")
    @Mapping(source = "platform.name", target = "platformName")
    @Mapping(source = "platform.manufacturer", target = "manufacturer")
    GamePlatformDetailDTO toDetailDTO(GamePlatform gamePlatform);

    // 3. Individual Review mapping
    // MapStruct uses this specific rule to transform each item in that list
    @Mapping(source = "user.username", target = "username")
    ReviewSummaryDTO toReviewSummaryDTO(Review review);

    GamePlatform toEntity(GamePlatformResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGamePlatformFromDto(@MappingTarget GamePlatform entity, GamePlatformResponseDTO dto);
}
