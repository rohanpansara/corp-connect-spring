package com.employee_self_service.services.client;

import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.dtos.users.card.DashboardCardDTO;
import com.employee_self_service.entities.users.User;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.security.dtos.RegisterDTO;

import java.util.List;
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
    public DashboardCardDTO getDashboardCards();
}
