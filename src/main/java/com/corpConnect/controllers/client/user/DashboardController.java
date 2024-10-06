package com.corpConnect.controllers.client.user;

import com.corpConnect.dtos.users.card.DashboardCardDTO;
import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.services.users.UserService;
import com.corpConnect.utils.constants.CorpConnectConstants;
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
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.Record.RECORD_FOUND, userService.getDashboardCards()));
    }

}
