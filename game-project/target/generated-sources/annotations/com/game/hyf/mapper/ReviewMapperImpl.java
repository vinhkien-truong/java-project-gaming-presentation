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
    date = "2026-05-12T03:03:33+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Homebrew)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review toEntity(ReviewResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.id( dto.getId() );
        review.rating( dto.getRating() );
        review.comment( dto.getComment() );

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
        reviewResponseDTO.rating( review.getRating() );
        reviewResponseDTO.comment( review.getComment() );

        return reviewResponseDTO.build();
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
