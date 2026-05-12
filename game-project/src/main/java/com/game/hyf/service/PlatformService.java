package com.game.hyf.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hyf.dto.platform.PlatformCreateDTO;
import com.game.hyf.dto.platform.PlatformResponseDTO;
import com.game.hyf.dto.platform.PlatformUpdateDTO;
import com.game.hyf.exception.PlatformNotFoundException;
import com.game.hyf.mapper.PlatformMapper;
import com.game.hyf.model.Platform;
import com.game.hyf.repository.PlatformRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlatformService {
    private final PlatformRepository repository;
    private final PlatformMapper mapper;

    @Transactional(readOnly = true)
    public List<PlatformResponseDTO> getAllPlatforms() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlatformResponseDTO getById(UUID id) {

        Platform platform = repository.findById(id)
                .orElseThrow(() -> new PlatformNotFoundException(id));

        return mapper.toDTO(platform);
    }

    @Transactional
    public PlatformResponseDTO create(PlatformCreateDTO dto) {

        Platform platform = mapper.toEntity(dto);

        return mapper.toDTO(repository.save(platform));
    }

    @Transactional
    public void delete(UUID id) {

        Platform platform = repository.findById(id)
                .orElseThrow(() -> new PlatformNotFoundException(id));

        repository.delete(platform);
    }

    @Transactional
    public PlatformResponseDTO update(UUID id, PlatformUpdateDTO dto) {
        Platform platform = repository.findById(id)
                .orElseThrow(() -> new PlatformNotFoundException(id));
        if (dto.getName() != null)
            platform.setName(dto.getName());
        if (dto.getManufacturer() != null)
            platform.setManufacturer(dto.getManufacturer());
        return mapper.toDTO(repository.save(platform));
    }
}
