package com.game.hyf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.game.hyf.dto.review.ReviewResponseDTO;
import com.game.hyf.model.Review;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    Review toEntity(ReviewResponseDTO dto);
    @Mapping(source = "gamePlatform.id", target = "gamePlatformId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "review.id", target = "id")
    ReviewResponseDTO toDTO(Review review);
}
