package com.employee_self_service.services.users;

import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.dtos.users.card.DashboardCardDTO;
import com.employee_self_service.entities.users.Users;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.security.dtos.RegisterDTO;

import java.util.List;

public interface UserService {

    // User Mapper
    Users getUserFromRegisterDTO(RegisterDTO registerDTO) throws BaseException;
    Users getEntity(UserDTO userDTO);
    UserDTO getDTO(Users users);
    List<Users> getEntityList(List<UserDTO> userDTOList);
    List<UserDTO> getDTOList(List<Users> usersList);

    // User Repository
    Users finalSave(Users users);
    Users getUserByEmail(String email);
    Users getUserByUserId(Long userId);
    List<Users> getUserByAccountExpiration(Boolean isExpired);
    List<Users> getAllUsers();

    // Dashboard
    DashboardCardDTO getDashboardCards();


    // HR Access Control
    void unlockUserAccount(Long userId);
    void enableUserAccount(Long userId);
    void disableUserAccount(Long userId);
    void lockUserAccount(Long userId);
}
