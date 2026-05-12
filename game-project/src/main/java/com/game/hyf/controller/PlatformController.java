package com.game.hyf.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.game.hyf.dto.platform.PlatformCreateDTO;
import com.game.hyf.dto.platform.PlatformResponseDTO;
import com.game.hyf.service.PlatformService;
import com.game.hyf.dto.platform.PlatformUpdateDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService service;

    @GetMapping
    public ResponseEntity<List<PlatformResponseDTO>> getAllPlatforms() {

        return ResponseEntity.ok(service.getAllPlatforms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponseDTO> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PlatformResponseDTO> create(
            @RequestBody @Valid PlatformCreateDTO dto
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
    public ResponseEntity<PlatformResponseDTO> updatePlatform(
            @PathVariable UUID id,
            @RequestBody PlatformUpdateDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}