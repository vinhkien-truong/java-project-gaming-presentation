package com.game.hyf.mapper;

import org.mapstruct.*;

import com.game.hyf.dto.review.ReviewDetailDTO;
import com.game.hyf.dto.review.ReviewResponseDTO;
import com.game.hyf.model.Review;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    Review toEntity(ReviewResponseDTO dto);
    @Mapping(source = "gamePlatform.id", target = "gamePlatformId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "review.id", target = "id")
    ReviewResponseDTO toDTO(Review review);

    //source is from entity, target is from DTO
    @Mapping(source = "gamePlatform.id", target = "gamePlatformId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "review.id", target = "id")
    @Mapping(source = "gamePlatform.game.title", target = "gameName")
    @Mapping(source = "gamePlatform.platform.name", target = "platformName")
    @Mapping(source = "gamePlatform.platform.manufacturer", target = "manufacturer")
    @Mapping(source = "user.username", target = "username")
    ReviewDetailDTO toDetailDTO(Review review);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReviewFromDto(@MappingTarget Review entity, ReviewResponseDTO dto);
}
