package com.corpConnect.services.user;

import com.corpConnect.dtos.card.dashboard.RightSideCardsDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.dtos.card.dashboard.LeftSideCardsDTO;
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
    String getUsernameByUserId(Long userId);
    List<User> getUserByAccountExpiration(Boolean isExpired);
    List<User> getAllUsers(Boolean isDeleted);
    List<User> getAllNonDeletedUsers();
    List<User> getAllDeletedUsers();
    User updateUserByUserIdAndUserDTO(Long userId, UserDTO userDTO);
    void deleteUserByUserId(Long userId);
    void deleteUsersByUserIdList(List<Long> userIdList);

    // Dashboard
    LeftSideCardsDTO getLeftSideCards();
    RightSideCardsDTO getRightSideCards();

    // HR Access Control
    void unlockUserAccount(Long userId);
    void enableUserAccount(Long userId);
    void disableUserAccount(Long userId);
    void lockUserAccount(Long userId);
}
