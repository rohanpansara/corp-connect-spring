package com.employee_self_service.services.users;

import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.dtos.users.card.DashboardCardDTO;
import com.employee_self_service.entities.users.User;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.security.dtos.RegisterDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // User Mapper
    User getUserFromRegisterDTO(RegisterDTO registerDTO) throws BaseException;
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

    // Dashboard
    DashboardCardDTO getDashboardCards();


    // HR Access Control
    void unlockUserAccount(Long userId);
    void enableUserAccount(Long userId);
    void disableUserAccount(Long userId);
    void lockUserAccount(Long userId);
}
