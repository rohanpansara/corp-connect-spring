package com.corpConnect.controllers.user;

import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/employee")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> fetchAllUsers(@RequestParam(required = false, value = "deleted") Boolean isDeleted){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_LIST_FOUND, userService.getDTOList(userService.getAllUsers(isDeleted))));
    }

    @GetMapping("/{user-id}")
    @PreAuthorize("hasAuthority('admin:read') or @corpConnectUserContext.isLoggedUser(#userId)")
    public ResponseEntity<ResponseDTO<UserDTO>> fetchUserByUserId(@PathVariable("user-id") Long userId) throws BaseException {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_FOUND, userService.getDTO(userService.getUserByUserId(userId))));
    }

    @DeleteMapping("/{user-id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<Void>> deleteUserByUserId(@PathVariable("user-id") Long userId) throws BaseException {
        userService.deleteUserByUserId(userId);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_DELETED));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<Void>> deleteUserByUserIdList(@RequestBody List<Long> userIdList) throws BaseException {
        userService.deleteUsersByUserIdList(userIdList);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_DELETED));
    }
}
