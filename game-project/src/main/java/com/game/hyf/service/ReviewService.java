package com.game.hyf.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hyf.dto.review.ReviewCreateDTO;
import com.game.hyf.dto.review.ReviewDetailDTO;
import com.game.hyf.dto.review.ReviewResponseDTO;
import com.game.hyf.dto.review.ReviewUpdateDTO;
import com.game.hyf.exception.GamePlatformNotFoundException;
import com.game.hyf.exception.ReviewNotFoundException;
import com.game.hyf.exception.UserNotFoundException;
import com.game.hyf.mapper.ReviewMapper;
import com.game.hyf.model.GamePlatform;
import com.game.hyf.model.Review;
import com.game.hyf.model.User;
import com.game.hyf.repository.GamePlatformRepository;
import com.game.hyf.repository.ReviewRepository;
import com.game.hyf.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository repository;
	private final GamePlatformRepository gpRepository;
	private final UserRepository userRepository;
	private final ReviewMapper mapper;

	@Transactional(readOnly = true)
	public List<ReviewDetailDTO> getAll() {

		return repository.findAll()
				.stream()
				.map(mapper::toDetailDTO)
				.sorted((r1, r2) -> r2.getRating().compareTo(r1.getRating())) // Sort by rating descending
				.toList();
	}

	@Transactional(readOnly = true)
	public ReviewDetailDTO getById(UUID id) {

		Review review = repository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException(id));

		return mapper.toDetailDTO(review);
	}

	@Transactional
	public ReviewResponseDTO create(ReviewCreateDTO dto) {

		UUID gamePlatformId = dto.getGamePlatformId();
		UUID userId = dto.getUserId();

		GamePlatform gp = gpRepository.findById(gamePlatformId)
				.orElseThrow(() -> new GamePlatformNotFoundException(
						gamePlatformId));

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));

		Review review = Review.builder()
				.rating(dto.getRating())
				.comment(dto.getComment())
				.gamePlatform(gp)
				.user(user)
				.build();

		return mapper.toDTO(repository.save(review));
	}

	@Transactional
	public void delete(UUID id) {

		Review review = repository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException(id));

		repository.delete(review);
	}

	@Transactional
	public ReviewResponseDTO update(UUID id, ReviewUpdateDTO dto) {
		Review review = repository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException(id));
		if (dto.getRating() != null)
			review.setRating(dto.getRating());
		if (dto.getComment() != null)
			review.setComment(dto.getComment());
		return mapper.toDTO(repository.save(review));
	}
}
