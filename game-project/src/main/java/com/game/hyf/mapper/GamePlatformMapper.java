package com.game.hyf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.game.hyf.dto.gameplatform.GamePlatformResponseDTO;
import com.game.hyf.model.GamePlatform;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GamePlatformMapper {
    GamePlatform toEntity(GamePlatformResponseDTO dto);
    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "platform.id", target = "platformId")
    GamePlatformResponseDTO toDTO(GamePlatform gamePlatform);
}
