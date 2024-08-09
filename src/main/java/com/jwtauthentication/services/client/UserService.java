package com.jwtauthentication.services.client;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.dtos.client.dashboard.CardDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.security.dtos.RegisterDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    // User Mapper
    public User getUserFromRegisterDTO(RegisterDTO registerDTO) throws BaseException;

    public User getEntity(UserDTO userDTO);

    public UserDTO getDTO(User user);

    public List<User> getEntityList(List<UserDTO> userDTOList);

    public List<UserDTO> getDTOList(List<User> userList);


    // User Repository
    public User finalSave(User user);

    public Optional<User> getUserByEmail(String email);

    public User getUserByUserId(Long userId);

    public List<User> getUserByAccountExpiration(Boolean isExpired);

    public List<User> getAllUsers();


    // HR Access Control
    public void unlockUserAccount(Long userId);

    public void enableUserAccount(Long userId);

    public void disableUserAccount(Long userId);

    public void lockUserAccount(Long userId);


    // Dashboard
    public Map<String, CardDTO> getDashboardCards();
}
