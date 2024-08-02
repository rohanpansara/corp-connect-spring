package com.jwtauthentication.services;

import com.jwtauthentication.audits.ApplicationAuditAware;
import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.client.UserNotFoundException;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.mappers.client.UserMapper;
import com.jwtauthentication.repositories.client.UserRepository;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.utils.EssConstants;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final ApplicationAuditAware applicationAuditAware;

    public User getUserFromRegisterDTO(RegisterDTO registerDTO) throws BaseException {
        return userMapper.toEntityFromRegisterDTO(registerDTO);
    }

    public User getEntity(UserDTO userDTO) {
        return userMapper.toEntity(userDTO);
    }

    public UserDTO getDTO(User user) {
        return userMapper.toDTO(user);
    }

    public List<User> getEntityList(List<UserDTO> userDTOList) {
        return userMapper.toEntityList(userDTOList);
    }

    public List<UserDTO> getDTOList(List<User> userList) {
        return userMapper.toDTOList(userList);
    }

    @Transactional
    public User finalSave(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setCreatedBy(applicationAuditAware.getCurrentAuditor().orElse("unknown"));
        user.setLastUpdatedBy(applicationAuditAware.getCurrentAuditor().orElse("unknown"));
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByUserId(Long userId) {
        return userRepository.findUserById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void unlockUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (user.isAccountNonLocked()) {
            return;
        }
        user.setAccountNonLocked(true);
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void enableUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (user.isAccountEnabled()) {
            return;
        }
        user.setAccountEnabled(true);
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void disableUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (!user.isAccountEnabled()) {
            return;
        }
        user.setAccountEnabled(false);
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void lockUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (!user.isAccountNonLocked()) {
            return;
        }
        user.setAccountNonLocked(false);
        userRepository.saveAndFlush(user);
    }

}
