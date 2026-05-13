package com.game.hyf.mapper;

import com.game.hyf.dto.gameplatform.GamePlatformDetailDTO;
import com.game.hyf.dto.gameplatform.GamePlatformResponseDTO;
import com.game.hyf.model.Game;
import com.game.hyf.model.GamePlatform;
import com.game.hyf.model.Platform;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T15:05:13+0200",
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

    @Override
    public GamePlatformDetailDTO toDetailDTO(GamePlatform gamePlatform) {
        if ( gamePlatform == null ) {
            return null;
        }

        GamePlatformDetailDTO.GamePlatformDetailDTOBuilder gamePlatformDetailDTO = GamePlatformDetailDTO.builder();

        gamePlatformDetailDTO.gameId( gamePlatformGameId( gamePlatform ) );
        gamePlatformDetailDTO.platformId( gamePlatformPlatformId( gamePlatform ) );
        gamePlatformDetailDTO.gameName( gamePlatformGameTitle( gamePlatform ) );
        gamePlatformDetailDTO.platformName( gamePlatformPlatformName( gamePlatform ) );
        gamePlatformDetailDTO.manufacturer( gamePlatformPlatformManufacturer( gamePlatform ) );
        gamePlatformDetailDTO.id( gamePlatform.getId() );
        gamePlatformDetailDTO.price( gamePlatform.getPrice() );
        gamePlatformDetailDTO.format( gamePlatform.getFormat() );

        return gamePlatformDetailDTO.build();
    }

    @Override
    public void updateGamePlatformFromDto(GamePlatform entity, GamePlatformResponseDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getFormat() != null ) {
            entity.setFormat( dto.getFormat() );
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

    private String gamePlatformGameTitle(GamePlatform gamePlatform) {
        Game game = gamePlatform.getGame();
        if ( game == null ) {
            return null;
        }
        return game.getTitle();
    }

    private String gamePlatformPlatformName(GamePlatform gamePlatform) {
        Platform platform = gamePlatform.getPlatform();
        if ( platform == null ) {
            return null;
        }
        return platform.getName();
    }

    private String gamePlatformPlatformManufacturer(GamePlatform gamePlatform) {
        Platform platform = gamePlatform.getPlatform();
        if ( platform == null ) {
            return null;
        }
        return platform.getManufacturer();
    }
}
