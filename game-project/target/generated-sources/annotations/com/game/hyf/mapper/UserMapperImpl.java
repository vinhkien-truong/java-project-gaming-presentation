package com.game.hyf.mapper;

import com.game.hyf.dto.user.UserCreateDTO;
import com.game.hyf.dto.user.UserResponseDTO;
import com.game.hyf.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T16:31:30+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.getUsername() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setRole( dto.getRole() );
        user.setCountry( dto.getCountry() );

        return user;
    }

    @Override
    public UserResponseDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.id( user.getId() );
        userResponseDTO.username( user.getUsername() );
        userResponseDTO.email( user.getEmail() );
        if ( user.getRole() != null ) {
            userResponseDTO.role( user.getRole().name() );
        }
        userResponseDTO.country( user.getCountry() );
        userResponseDTO.createdAt( user.getCreatedAt() );
        userResponseDTO.updatedAt( user.getUpdatedAt() );

        return userResponseDTO.build();
    }

    @Override
    public void updateUserFromDto(User entity, UserCreateDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUsername() != null ) {
            entity.setUsername( dto.getUsername() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getRole() != null ) {
            entity.setRole( dto.getRole() );
        }
        if ( dto.getCountry() != null ) {
            entity.setCountry( dto.getCountry() );
        }
    }
}
