package com.game.hyf.mapper;

import com.game.hyf.dto.platform.PlatformCreateDTO;
import com.game.hyf.dto.platform.PlatformResponseDTO;
import com.game.hyf.model.Platform;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-15T02:03:21+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class PlatformMapperImpl implements PlatformMapper {

    @Override
    public Platform toEntity(PlatformCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Platform platform = new Platform();

        platform.setName( dto.getName() );
        platform.setManufacturer( dto.getManufacturer() );

        return platform;
    }

    @Override
    public PlatformResponseDTO toDTO(Platform platform) {
        if ( platform == null ) {
            return null;
        }

        PlatformResponseDTO.PlatformResponseDTOBuilder platformResponseDTO = PlatformResponseDTO.builder();

        platformResponseDTO.id( platform.getId() );
        platformResponseDTO.name( platform.getName() );
        platformResponseDTO.manufacturer( platform.getManufacturer() );
        platformResponseDTO.createdAt( platform.getCreatedAt() );
        platformResponseDTO.updatedAt( platform.getUpdatedAt() );

        return platformResponseDTO.build();
    }

    @Override
    public void updatePlatformFromDto(Platform entity, PlatformCreateDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getManufacturer() != null ) {
            entity.setManufacturer( dto.getManufacturer() );
        }
    }
}
