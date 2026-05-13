package com.game.hyf.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.game.hyf.dto.gameplatform.GamePlatformDetailDTO;
import com.game.hyf.dto.review.ReviewDetailDTO;
import com.game.hyf.model.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("""
        SELECT new com.game.hyf.dto.review.ReviewDetailDTO(
            r.id, 
            gp.id, 
            u.id,
            g.title, 
            p.name,
            p.manufacturer,
            u.username,
            r.rating, 
            r.comment
        )
        FROM Review r
        JOIN r.gamePlatform gp
        JOIN gp.game g
        JOIN gp.platform p
        JOIN r.user u
        WHERE r.id = :id
    """)
    Optional<ReviewDetailDTO> findByIdWithDetails(UUID id);

    @Query("""
        SELECT new com.game.hyf.dto.review.ReviewDetailDTO(
            r.id, 
            gp.id, 
            u.id,
            g.title, 
            p.name,
            p.manufacturer,
            u.username,
            r.rating, 
            r.comment
        )
        FROM Review r
        JOIN r.gamePlatform gp
        JOIN gp.game g
        JOIN gp.platform p
        JOIN r.user u
    """)
    List<ReviewDetailDTO> findAllDetails();

}
