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
    @Query("""
        SELECT new com.game.hyf.dto.gameplatform.GamePlatformDetailDTO(
            gp.id, 
            g.id,
            p.id,
            g.title, 
            p.name,
            p.manufacturer,
            gp.price, 
            gp.format
        )
        FROM GamePlatform gp
        JOIN gp.game g
        JOIN gp.platform p
        WHERE gp.id = :id
    """)
    Optional<GamePlatformDetailDTO> findDetailById(@Param("id") UUID id);

    @Query("""
    SELECT new com.game.hyf.dto.gameplatform.GamePlatformDetailDTO(
        gp.id, 
        g.id, 
        p.id, 
        g.title, 
        p.name,
        p.manufacturer,
        gp.price, 
        gp.format
    )
    FROM GamePlatform gp
    JOIN gp.game g
    JOIN gp.platform p
    """)
    List<GamePlatformDetailDTO> findAllDetails();
}
