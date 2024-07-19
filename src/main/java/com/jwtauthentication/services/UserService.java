package com.jwtauthentication.services;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.mappers.client.UserMapper;
import com.jwtauthentication.repositories.UserRepository;
import com.jwtauthentication.security.dtos.RegisterDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User getEntityFromRegisterDTO(RegisterDTO registerDTO){
        return userMapper.toEntityFromRegisterDTO(registerDTO);
    }

    public User getEntity(UserDTO userDTO){
        return userMapper.toEntity(userDTO);
    }

    public UserDTO getDTO(User user){
        return userMapper.toDTO(user);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
