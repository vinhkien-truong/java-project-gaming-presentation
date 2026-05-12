package com.game.hyf.mapper;

import com.game.hyf.dto.user.UserCreateDTO;
import com.game.hyf.dto.user.UserResponseDTO;
import com.game.hyf.model.User;
import com.game.hyf.model.UserRole;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-12T03:03:33+0200",
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
        if ( dto.getRole() != null ) {
            user.setRole( Enum.valueOf( UserRole.class, dto.getRole() ) );
        }
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
}
