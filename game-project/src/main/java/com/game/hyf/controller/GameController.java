package com.game.hyf.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.game.hyf.dto.game.GameCreateDTO;
import com.game.hyf.dto.game.GameResponseDTO;
import com.game.hyf.service.GameService;
import com.game.hyf.dto.game.GameUpdateDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService service;

    @GetMapping
    public ResponseEntity<List<GameResponseDTO>> getAllGames() {

        return ResponseEntity.ok(service.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> getGameById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(service.getGameById(id));
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> createGame(
            @RequestBody @Valid GameCreateDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameResponseDTO> updateGame(
            @PathVariable UUID id,
            @RequestBody GameUpdateDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

}
