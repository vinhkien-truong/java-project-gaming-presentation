package com.game.hyf.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hyf.dto.game.GameCreateDTO;
import com.game.hyf.dto.game.GameResponseDTO;
import com.game.hyf.dto.game.GameUpdateDTO;
import com.game.hyf.exception.GameNotFoundException;
import com.game.hyf.mapper.GameMapper;
import com.game.hyf.model.Game;
import com.game.hyf.repository.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final GameMapper mapper;

    @Transactional(readOnly = true)
    public List<GameResponseDTO> getAllGames() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public GameResponseDTO getGameById(UUID id) {

        Game game = repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        return mapper.toDTO(game);
    }

    @Transactional
    public GameResponseDTO create(GameCreateDTO dto) {

        Game game = mapper.toEntity(dto);

        return mapper.toDTO(repository.save(game));
    }

    @Transactional
    public void delete(UUID id) {

        Game game = repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        repository.delete(game);
    }

    @Transactional
    public GameResponseDTO update(UUID id, GameUpdateDTO dto) {
        Game game = repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        if (dto.getTitle() != null)
            game.setTitle(dto.getTitle());
        if (dto.getReleaseDate() != null)
            game.setReleaseDate(dto.getReleaseDate());
        if (dto.getDescription() != null)
            game.setDescription(dto.getDescription());
        if (dto.getVersion() != null)
            game.setVersion(dto.getVersion());
        return mapper.toDTO(repository.save(game));
    }
}
