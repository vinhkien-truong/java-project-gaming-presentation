package com.game.hyf.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.hyf.model.GameFormat;
import com.game.hyf.model.GamePlatform;

public interface GamePlatformRepository extends JpaRepository<GamePlatform, UUID> {
    boolean existsByGameIdAndPlatformIdAndFormat(UUID gameId, UUID platformId, GameFormat format);
}
