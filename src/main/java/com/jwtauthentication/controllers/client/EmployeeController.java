package com.jwtauthentication.controllers.client;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.dtos.common.ResponseDTO;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.services.UserService;
import com.jwtauthentication.utils.EssConstants;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/auth/employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {

    private final UserService userService;

    @Operation(summary = "Find User By user-id", description = "We use the repository method to find the Entity and then we map it to DTO.")
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<UserDTO>> fetchUserByUserId(@PathVariable("userId") Long userId) throws BaseException {
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.USER_FOUND, userService.getDTO(userService.getUserByUserId(userId))));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> fetchAllUsers(){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.USER_LIST_FOUND, userService.getDTOList(userService.getAllUsers())));
    }
}
