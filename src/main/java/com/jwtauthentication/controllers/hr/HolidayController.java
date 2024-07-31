package com.jwtauthentication.controllers.hr;

import com.jwtauthentication.dtos.common.ResponseDTO;
import com.jwtauthentication.dtos.hr.HolidayDTO;
import com.jwtauthentication.services.hr.HolidayService;
import com.jwtauthentication.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/hr/holiday")
@PreAuthorize("hasRole('ADMIN')")
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<ResponseDTO<HolidayDTO>> createHoliday(@RequestBody HolidayDTO holidayDTO){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Holiday.HOLIDAY_CREATED, holidayService.createHoliday(holidayService.getEntity(holidayDTO))));
    }

}
