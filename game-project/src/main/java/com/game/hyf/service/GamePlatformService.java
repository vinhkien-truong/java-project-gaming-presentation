package com.game.hyf.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hyf.dto.gameplatform.GamePlatformCreateDTO;
import com.game.hyf.dto.gameplatform.GamePlatformDetailDTO;
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

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GamePlatformService {

	private final GamePlatformRepository repository;
	private final GamePlatformMapper mapper;
	private final GameRepository gameRepository;
	private final PlatformRepository platformRepository;

	@Transactional(readOnly = true)
	public List<GamePlatformDetailDTO> getAll() {
		List<GamePlatform> entities = repository.findAllWithDetails();
		return entities.stream()
                   .map(mapper::toDetailDTO)
				   .sorted((a, b) -> {
						int gameNameComparison = a.getGameName().compareToIgnoreCase(b.getGameName());
						if (gameNameComparison != 0) {
							return gameNameComparison;
						}
						return a.getManufacturer().compareToIgnoreCase(b.getManufacturer());
					})
                   .toList();
	}

	@Transactional(readOnly = true)
	public GamePlatformDetailDTO getById(UUID id) {

		GamePlatform entity = repository.findDetailById(id)
        .orElseThrow(() -> new EntityNotFoundException("Platform not found"));
        
    	return mapper.toDetailDTO(entity);
	}

	@Transactional
	public GamePlatformResponseDTO create(
			GamePlatformCreateDTO dto) {
		UUID gameId = dto.getGameId();
		UUID platformId = dto.getPlatformId();

		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new GameNotFoundException(gameId));

		Platform platform = platformRepository.findById(platformId)
				.orElseThrow(() -> new PlatformNotFoundException(platformId));
		boolean exists = repository.existsByGameIdAndPlatformIdAndFormat(
				gameId,
				platformId,
				dto.getFormat());
		if (exists) {
			throw new IllegalArgumentException(
					"GamePlatform already exists for the given game and platform");
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
				.orElseThrow(() -> new GamePlatformNotFoundException(id));

		repository.delete(gp);
	}

	@Transactional
	public GamePlatformResponseDTO update(UUID id, GamePlatformUpdateDTO dto) {
		GamePlatform gp = repository.findById(id)
				.orElseThrow(() -> new GamePlatformNotFoundException(id));
		if (dto.getPrice() != null)
			gp.setPrice(dto.getPrice());
		return mapper.toDTO(repository.save(gp));
	}
}
