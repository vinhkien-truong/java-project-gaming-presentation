package com.game.hyf.mapper;

import com.game.hyf.dto.review.ReviewDetailDTO;
import com.game.hyf.dto.review.ReviewResponseDTO;
import com.game.hyf.model.Game;
import com.game.hyf.model.GamePlatform;
import com.game.hyf.model.Platform;
import com.game.hyf.model.Review;
import com.game.hyf.model.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-15T02:03:21+0200",
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

    @Override
    public ReviewDetailDTO toDetailDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDetailDTO.ReviewDetailDTOBuilder reviewDetailDTO = ReviewDetailDTO.builder();

        reviewDetailDTO.gamePlatformId( reviewGamePlatformId( review ) );
        reviewDetailDTO.userId( reviewUserId( review ) );
        reviewDetailDTO.id( review.getId() );
        reviewDetailDTO.gameName( reviewGamePlatformGameTitle( review ) );
        reviewDetailDTO.platformName( reviewGamePlatformPlatformName( review ) );
        reviewDetailDTO.manufacturer( reviewGamePlatformPlatformManufacturer( review ) );
        reviewDetailDTO.username( reviewUserUsername( review ) );
        reviewDetailDTO.rating( review.getRating() );
        reviewDetailDTO.comment( review.getComment() );

        return reviewDetailDTO.build();
    }

    @Override
    public void updateReviewFromDto(Review entity, ReviewResponseDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getRating() != null ) {
            entity.setRating( dto.getRating() );
        }
        if ( dto.getComment() != null ) {
            entity.setComment( dto.getComment() );
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

    private String reviewGamePlatformGameTitle(Review review) {
        GamePlatform gamePlatform = review.getGamePlatform();
        if ( gamePlatform == null ) {
            return null;
        }
        Game game = gamePlatform.getGame();
        if ( game == null ) {
            return null;
        }
        return game.getTitle();
    }

    private String reviewGamePlatformPlatformName(Review review) {
        GamePlatform gamePlatform = review.getGamePlatform();
        if ( gamePlatform == null ) {
            return null;
        }
        Platform platform = gamePlatform.getPlatform();
        if ( platform == null ) {
            return null;
        }
        return platform.getName();
    }

    private String reviewGamePlatformPlatformManufacturer(Review review) {
        GamePlatform gamePlatform = review.getGamePlatform();
        if ( gamePlatform == null ) {
            return null;
        }
        Platform platform = gamePlatform.getPlatform();
        if ( platform == null ) {
            return null;
        }
        return platform.getManufacturer();
    }

    private String reviewUserUsername(Review review) {
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUsername();
    }
}
