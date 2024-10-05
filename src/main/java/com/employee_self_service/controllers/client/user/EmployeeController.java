package com.employee_self_service.controllers.client.user;

import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.dtos.common.ResponseDTO;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.services.users.UserService;
import com.employee_self_service.utils.constants.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/employee")
public class EmployeeController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> fetchAllUsers(){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.USER_LIST_FOUND, userService.getDTOList(userService.getAllUsers())));
    }

    @GetMapping("/{user-id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<UserDTO>> fetchUserByUserId(@PathVariable("user-id") Long userId) throws BaseException {
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.USER_FOUND, userService.getDTO(userService.getUserByUserId(userId))));
    }
}
