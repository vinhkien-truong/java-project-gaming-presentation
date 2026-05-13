package com.game.hyf.mapper;

import com.game.hyf.dto.review.ReviewResponseDTO;
import com.game.hyf.model.GamePlatform;
import com.game.hyf.model.Review;
import com.game.hyf.model.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T01:45:53+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review toEntity(ReviewResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.comment( dto.getComment() );
        review.id( dto.getId() );
        review.rating( dto.getRating() );

        return review.build();
    }

    @Override
    public ReviewResponseDTO toDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponseDTO.ReviewResponseDTOBuilder reviewResponseDTO = ReviewResponseDTO.builder();

        reviewResponseDTO.gamePlatformId( reviewGamePlatformId( review ) );
        reviewResponseDTO.userId( reviewUserId( review ) );
        reviewResponseDTO.id( review.getId() );
        reviewResponseDTO.comment( review.getComment() );
        reviewResponseDTO.rating( review.getRating() );

        return reviewResponseDTO.build();
    }

    @Override
    public void updateReviewFromDto(Review entity, ReviewResponseDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getComment() != null ) {
            entity.setComment( dto.getComment() );
        }
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getRating() != null ) {
            entity.setRating( dto.getRating() );
        }
    }

    private UUID reviewGamePlatformId(Review review) {
        GamePlatform gamePlatform = review.getGamePlatform();
        if ( gamePlatform == null ) {
            return null;
        }
        return gamePlatform.getId();
    }

    private UUID reviewUserId(Review review) {
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
}
