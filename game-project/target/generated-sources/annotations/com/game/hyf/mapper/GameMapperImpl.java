package com.game.hyf.mapper;

import com.game.hyf.dto.game.GameCreateDTO;
import com.game.hyf.dto.game.GameResponseDTO;
import com.game.hyf.model.Game;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T01:45:53+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public Game toEntity(GameCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Game game = new Game();

        game.setDescription( dto.getDescription() );
        game.setReleaseDate( dto.getReleaseDate() );
        game.setTitle( dto.getTitle() );
        game.setVersion( dto.getVersion() );

        return game;
    }

    @Override
    public GameResponseDTO toDTO(Game game) {
        if ( game == null ) {
            return null;
        }

        GameResponseDTO.GameResponseDTOBuilder gameResponseDTO = GameResponseDTO.builder();

        gameResponseDTO.createdAt( game.getCreatedAt() );
        gameResponseDTO.description( game.getDescription() );
        gameResponseDTO.id( game.getId() );
        gameResponseDTO.releaseDate( game.getReleaseDate() );
        gameResponseDTO.title( game.getTitle() );
        gameResponseDTO.version( game.getVersion() );

        return gameResponseDTO.build();
    }

    @Override
    public void updateGameFromDto(Game entity, GameCreateDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
        if ( dto.getReleaseDate() != null ) {
            entity.setReleaseDate( dto.getReleaseDate() );
        }
        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getVersion() != null ) {
            entity.setVersion( dto.getVersion() );
        }
    }
}
