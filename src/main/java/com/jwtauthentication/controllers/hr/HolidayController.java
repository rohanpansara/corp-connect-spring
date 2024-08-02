package com.jwtauthentication.controllers.hr;

import com.jwtauthentication.dtos.common.ResponseDTO;
import com.jwtauthentication.dtos.hr.HolidayDTO;
import com.jwtauthentication.services.hr.HolidayService;
import com.jwtauthentication.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/holiday")
@PreAuthorize("hasRole('HR_ADMIN')")
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<ResponseDTO<Void>> createHoliday(@RequestBody HolidayDTO holidayDTO){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Holiday.HOLIDAY_CREATED));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidayDTO>>> fetchAllHolidays(){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Holiday.HOLIDAY_LIST_FOUND, holidayService.getDTOList(holidayService.getAllHolidays())));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidayDTO>>> fetchHolidayByHolidayId(@PathVariable("id") Long holidayId){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Holiday.HOLIDAY_FOUND, holidayService.getDTOList(holidayService.getHolidayByHolidayId(holidayId))));
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidayDTO>>> fetchAllHolidaysByHolidayName(@RequestParam("holidayName") String holidayName){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Holiday.HOLIDAY_LIST_FOUND, holidayService.getDTOList(holidayService.getHolidayByHolidayName(holidayName))));
    }

    @GetMapping("/date")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidayDTO>>> fetchHolidayByHolidayDate(@RequestParam("holidayDate") String holidayDate){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Holiday.HOLIDAY_FOUND, holidayService.getDTOList(holidayService.getHolidayByHolidayDate(holidayDate))));
    }

    @GetMapping("/type")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidayDTO>>> fetchAllHolidaysByHolidayType(@RequestParam("holidayType") String label){
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.Holiday.HOLIDAY_LIST_FOUND, holidayService.getDTOList(holidayService.getAllHolidaysByHolidayType(label))));
    }
}
