package com.corpConnect.services.user;

import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.dtos.user.card.DashboardCardDTO;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.security.dtos.NewUserDTO;

import java.util.List;

public interface UserService {

    // User Mapper
    User getUserFromRegisterDTO(NewUserDTO newUserDTO) throws BaseException;
    User getEntity(UserDTO userDTO);
    UserDTO getDTO(User user);
    List<User> getEntityList(List<UserDTO> userDTOList);
    List<UserDTO> getDTOList(List<User> userList);

    // User Repository
    User finalSave(User user);
    User getUserByEmail(String email);
    User getUserByUserId(Long userId);
    List<User> getUserByAccountExpiration(Boolean isExpired);
    List<User> getAllUsers();
    List<User> getAllNonDeletedUsers();
    List<User> getAllDeletedUsers();

    // Dashboard
    DashboardCardDTO getDashboardCards();


    // HR Access Control
    void unlockUserAccount(Long userId);
    void enableUserAccount(Long userId);
    void disableUserAccount(Long userId);
    void lockUserAccount(Long userId);
}
