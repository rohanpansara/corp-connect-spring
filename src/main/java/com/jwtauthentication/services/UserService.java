package com.jwtauthentication.services;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.client.UserNotFoundException;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.mappers.client.UserMapper;
import com.jwtauthentication.repositories.client.UserRepository;
import com.jwtauthentication.security.EssUserContext;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.utils.EssConstants;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserFromRegisterDTO(RegisterDTO registerDTO) throws BaseException {
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

    public User finalSave(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(LocalDateTime.now());
        user.setLastUpdatedDate(LocalDateTime.now());
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        if (Objects.isNull(EssUserContext.getCurrentUser())) {
            user.setCreatedBy(String.valueOf(1));
            user.setLastUpdatedBy(String.valueOf(1));
//            throw new RuntimeException(EssConstants.UserError.NOT_LOGGED_IN);
        } else {
            user.setCreatedBy(String.valueOf(EssUserContext.getCurrentUser().getId()));
            user.setLastUpdatedBy(String.valueOf(EssUserContext.getCurrentUser().getId()));
        }
        return userRepository.save(user);
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
        if(user.isAccountEnabled()){
            return user;
        }
        user.setAccountEnabled(true);
        return userRepository.save(user);
    }

    @Transactional
    public void unlockUserAccount(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        if(user.isAccountNonLocked()){
            return;
        }
        user.setAccountNonLocked(true);
        userRepository.saveAndFlush(user);
    }

    public User disableUserAccount(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        if(!user.isAccountEnabled()){
            return user;
        }
        user.setAccountEnabled(false);
        return userRepository.save(user);
    }

}
