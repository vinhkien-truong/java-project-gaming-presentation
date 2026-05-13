package com.game.hyf.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.game.hyf.dto.review.ReviewCreateDTO;
import com.game.hyf.dto.review.ReviewDetailDTO;
import com.game.hyf.dto.review.ReviewResponseDTO;
import com.game.hyf.service.ReviewService;
import com.game.hyf.dto.review.ReviewUpdateDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @GetMapping
    public ResponseEntity<List<ReviewDetailDTO>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDetailDTO> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> create(
            @RequestBody @Valid ReviewCreateDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable UUID id,
            @RequestBody ReviewUpdateDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}