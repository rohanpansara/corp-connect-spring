package com.jwtauthentication.services;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.client.UserNotFoundException;
import com.jwtauthentication.mappers.client.UserMapper;
import com.jwtauthentication.repositories.client.UserRepository;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.utils.EssConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User getUserFromRegisterDTO(RegisterDTO registerDTO) {
        return userMapper.toEntityFromRegisterDTO(registerDTO);
    }

    public User getEntity(UserDTO userDTO){
        return userMapper.toEntity(userDTO);
    }

    public UserDTO getDTO(User user){
        return userMapper.toDTO(user);
    }

    public List<User> getEntityList(List<UserDTO> userDTOList){
        return userMapper.toEntityList(userDTOList);
    }

    public List<UserDTO> getDTOList(List<User> userList){
        return userMapper.toDTOList(userList);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserByUserId(Long userId){
        return userRepository.findUserById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User enableUserAccount(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        user.setAccountEnabled(true);
        return userRepository.save(user);
    }

    public User unlockUserAccount(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        user.setAccountNonLocked(true);
        return userRepository.save(user);
    }

    public User disableUserAccount(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        user.setAccountEnabled(false);
        return userRepository.save(user);
    }

}
