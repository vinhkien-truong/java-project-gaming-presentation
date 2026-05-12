package com.game.hyf.mapper;

import org.mapstruct.*;

import com.game.hyf.dto.platform.PlatformCreateDTO;
import com.game.hyf.dto.platform.PlatformResponseDTO;
import com.game.hyf.model.Platform;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlatformMapper {
    Platform toEntity(PlatformCreateDTO dto);
    PlatformResponseDTO toDTO(Platform platform);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePlatformFromDto(@MappingTarget Platform entity, PlatformCreateDTO dto);
}
