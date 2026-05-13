package com.game.hyf.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.hyf.dto.gameplatform.GamePlatformCreateDTO;
import com.game.hyf.dto.gameplatform.GamePlatformDetailDTO;
import com.game.hyf.dto.gameplatform.GamePlatformResponseDTO;
import com.game.hyf.dto.gameplatform.GamePlatformUpdateDTO;
import com.game.hyf.service.GamePlatformService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/game-platforms")
@RequiredArgsConstructor
public class GamePlatformController {

    private final GamePlatformService service;

    @GetMapping
    public ResponseEntity<List<GamePlatformDetailDTO>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamePlatformDetailDTO> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(service.getById(id));
    }
     @PostMapping
    public ResponseEntity<GamePlatformResponseDTO> create(
            @RequestBody @Valid GamePlatformCreateDTO dto
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
    public ResponseEntity<GamePlatformResponseDTO> updateGamePlatform(
            @PathVariable UUID id,
            @RequestBody GamePlatformUpdateDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}