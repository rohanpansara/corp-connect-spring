package com.corpConnect.controllers.user;

import com.corpConnect.dtos.card.dashboard.LeftSideCardsDTO;
import com.corpConnect.dtos.card.dashboard.RightSideCardsDTO;
import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.MessageConstants;
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

    @GetMapping(value = "/cards/left-up")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<LeftSideCardsDTO>> fetchUpperLeftCardsData() {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, userService.getUpperLeftSideCards()));
    }

    @GetMapping(value = "/cards/left-down")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<LeftSideCardsDTO>> fetchLowerLeftCardData() {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, userService.getLowerLeftSideCards()));
    }

    @GetMapping(value = "/cards/right")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<RightSideCardsDTO>> fetchDashboardAttendanceData() {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, userService.getRightSideCards()));
    }

}
