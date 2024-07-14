package com.jwtauthentication.mappers.client;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.security.dtos.RegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    User toEntityFromRegisterDTO(RegisterDTO registerDTO);
}
