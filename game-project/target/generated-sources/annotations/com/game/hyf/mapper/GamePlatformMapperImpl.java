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
    date = "2026-05-12T03:03:33+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Homebrew)"
)
@Component
public class GamePlatformMapperImpl implements GamePlatformMapper {

    @Override
    public GamePlatform toEntity(GamePlatformResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GamePlatform.GamePlatformBuilder gamePlatform = GamePlatform.builder();

        gamePlatform.id( dto.getId() );
        gamePlatform.price( dto.getPrice() );
        gamePlatform.format( dto.getFormat() );

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
        gamePlatformResponseDTO.id( gamePlatform.getId() );
        gamePlatformResponseDTO.price( gamePlatform.getPrice() );
        gamePlatformResponseDTO.format( gamePlatform.getFormat() );

        return gamePlatformResponseDTO.build();
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
