package com.game.hyf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.game.hyf.dto.user.UserCreateDTO;
import com.game.hyf.dto.user.UserResponseDTO;
import com.game.hyf.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserCreateDTO dto);
    @Mapping(source = "user.id", target = "id")
    UserResponseDTO toDTO(User user);
}
