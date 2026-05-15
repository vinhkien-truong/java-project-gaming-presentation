package com.game.hyf.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.game.hyf.dto.gameplatform.GamePlatformDetailDTO;
import com.game.hyf.model.GameFormat;
import com.game.hyf.model.GamePlatform;

public interface GamePlatformRepository extends JpaRepository<GamePlatform, UUID> {
    boolean existsByGameIdAndPlatformIdAndFormat(UUID gameId, UUID platformId, GameFormat format);
    // Custom query to fetch GamePlatform with all its details (game, platform, reviews) in one go.
    @Query("SELECT gp FROM GamePlatform gp " +
           "LEFT JOIN FETCH gp.game " +
           "LEFT JOIN FETCH gp.platform " +
           "LEFT JOIN FETCH gp.reviews " +
           "WHERE gp.id = :id")
    Optional<GamePlatform> findDetailById(@Param("id") UUID id);

    @Query("SELECT gp FROM GamePlatform gp " +
           "LEFT JOIN FETCH gp.game " +
           "LEFT JOIN FETCH gp.platform " +
           "LEFT JOIN FETCH gp.reviews")
    List<GamePlatform> findAllWithDetails();

  @Query("SELECT gp FROM GamePlatform gp LEFT JOIN FETCH gp.reviews WHERE gp.id = :id")
    Optional<GamePlatform> findByIdWithReviews(@Param("id") UUID id);
}
