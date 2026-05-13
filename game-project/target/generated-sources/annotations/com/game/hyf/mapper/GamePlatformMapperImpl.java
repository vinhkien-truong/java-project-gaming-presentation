package com.game.hyf.mapper;

import com.game.hyf.dto.gameplatform.GamePlatformResponseDTO;
import com.game.hyf.model.Game;
import com.game.hyf.model.GamePlatform;
import com.game.hyf.model.Platform;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T01:45:53+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class GamePlatformMapperImpl implements GamePlatformMapper {

    @Override
    public GamePlatform toEntity(GamePlatformResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GamePlatform.GamePlatformBuilder gamePlatform = GamePlatform.builder();

        gamePlatform.format( dto.getFormat() );
        gamePlatform.id( dto.getId() );
        gamePlatform.price( dto.getPrice() );

        return gamePlatform.build();
    }

    @Override
    public GamePlatformResponseDTO toDTO(GamePlatform gamePlatform) {
        if ( gamePlatform == null ) {
            return null;
        }

        GamePlatformResponseDTO.GamePlatformResponseDTOBuilder gamePlatformResponseDTO = GamePlatformResponseDTO.builder();

        gamePlatformResponseDTO.gameId( gamePlatformGameId( gamePlatform ) );
        gamePlatformResponseDTO.platformId( gamePlatformPlatformId( gamePlatform ) );
        gamePlatformResponseDTO.format( gamePlatform.getFormat() );
        gamePlatformResponseDTO.id( gamePlatform.getId() );
        gamePlatformResponseDTO.price( gamePlatform.getPrice() );

        return gamePlatformResponseDTO.build();
    }

    @Override
    public void updateGamePlatformFromDto(GamePlatform entity, GamePlatformResponseDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFormat() != null ) {
            entity.setFormat( dto.getFormat() );
        }
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
    }

    private UUID gamePlatformGameId(GamePlatform gamePlatform) {
        Game game = gamePlatform.getGame();
        if ( game == null ) {
            return null;
        }
        return game.getId();
    }

    private UUID gamePlatformPlatformId(GamePlatform gamePlatform) {
        Platform platform = gamePlatform.getPlatform();
        if ( platform == null ) {
            return null;
        }
        return platform.getId();
    }
}
