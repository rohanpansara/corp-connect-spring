package com.jwtauthentication.services;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.mappers.client.UserMapper;
import com.jwtauthentication.security.dtos.RegisterDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;

    public User getEntityFromRegisterDTO(RegisterDTO registerDTO){
        return userMapper.toEntityFromRegisterDTO(registerDTO);
    }

    public User getEntity(UserDTO userDTO){
        return userMapper.toEntity(userDTO);
    }

    public UserDTO getDTO(User user){
        return userMapper.toDTO(user);
    }
}
