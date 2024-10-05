package com.employee_self_service.controllers.client.user;

import com.employee_self_service.dtos.users.card.DashboardCardDTO;
import com.employee_self_service.dtos.common.ResponseDTO;
import com.employee_self_service.services.users.UserService;
import com.employee_self_service.utils.constants.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@RequestMapping("/dashboard")
public class DashboardController {
    private final UserService userService;

    @GetMapping(value = "/cards")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<DashboardCardDTO>> fetchDashboardCardData() {
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Record.RECORD_FOUND, userService.getDashboardCards()));
    }

}
