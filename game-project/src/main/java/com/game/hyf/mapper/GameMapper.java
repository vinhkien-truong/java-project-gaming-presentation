package com.game.hyf.mapper;

import org.mapstruct.*;

import com.game.hyf.dto.game.GameCreateDTO;
import com.game.hyf.dto.game.GameResponseDTO;
import com.game.hyf.model.Game;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {
    Game toEntity(GameCreateDTO dto);
    GameResponseDTO toDTO(Game game);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGameFromDto(@MappingTarget Game entity, GameCreateDTO dto);
}
