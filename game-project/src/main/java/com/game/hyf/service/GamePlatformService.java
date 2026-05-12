package com.game.hyf.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hyf.dto.gameplatform.GamePlatformCreateDTO;
import com.game.hyf.dto.gameplatform.GamePlatformResponseDTO;
import com.game.hyf.dto.gameplatform.GamePlatformUpdateDTO;
import com.game.hyf.exception.GameNotFoundException;
import com.game.hyf.exception.GamePlatformNotFoundException;
import com.game.hyf.exception.PlatformNotFoundException;
import com.game.hyf.mapper.GamePlatformMapper;
import com.game.hyf.model.Game;
import com.game.hyf.model.GamePlatform;
import com.game.hyf.model.Platform;
import com.game.hyf.repository.GamePlatformRepository;
import com.game.hyf.repository.GameRepository;
import com.game.hyf.repository.PlatformRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GamePlatformService {
    
    private final GamePlatformRepository repository;
    private final GamePlatformMapper mapper;
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;

        @Transactional(readOnly = true)
        public List<GamePlatformResponseDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

        @Transactional(readOnly = true)
        public GamePlatformResponseDTO getById(UUID id) {

        GamePlatform gp = repository.findById(id)
                .orElseThrow(() ->
                        new GamePlatformNotFoundException(id));

        return mapper.toDTO(gp);
    }
        @Transactional
        public GamePlatformResponseDTO create(
            GamePlatformCreateDTO dto
    ) {
        UUID gameId = dto.getGameId();
        UUID platformId = dto.getPlatformId();

        Game game = gameRepository.findById(gameId)
                 .orElseThrow(() ->
                         new GameNotFoundException(gameId));

        Platform platform = platformRepository.findById(platformId)
                .orElseThrow(() ->
                        new PlatformNotFoundException(platformId));
        boolean exists =
                repository.existsByGameIdAndPlatformIdAndFormat(
                        gameId,
                        platformId,
                        dto.getFormat()
                );
        if (exists) {
            throw new IllegalArgumentException("GamePlatform already exists for the given game and platform");
        }
        GamePlatform gamePlatform = new GamePlatform();

        gamePlatform.setGame(game);
        gamePlatform.setPlatform(platform);
        gamePlatform.setPrice(dto.getPrice());
        gamePlatform.setFormat(dto.getFormat());

        return mapper.toDTO(repository.save(gamePlatform));
    }

        @Transactional
        public void delete(UUID id) {

        GamePlatform gp = repository.findById(id)
                .orElseThrow(() ->
                        new GamePlatformNotFoundException(id));

        repository.delete(gp);
    }

    @Transactional
    public GamePlatformResponseDTO update(UUID id, GamePlatformUpdateDTO dto) {
        GamePlatform gp = repository.findById(id)
                .orElseThrow(() -> new GamePlatformNotFoundException(id));
        if (dto.getPrice() != null) gp.setPrice(dto.getPrice());
        if (dto.getFormat() != null) gp.setFormat(dto.getFormat());
        return mapper.toDTO(repository.save(gp));
    }
}
