package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.hr.HolidaysDTO;
import com.corpConnect.services.hr.HolidayService;
import com.corpConnect.utils.constants.CorpConnectConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/hr/holiday")
@PreAuthorize("hasRole('HR_ADMIN')")
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping
    @PreAuthorize("hasAuthority('hr_admin:create')")
    public ResponseEntity<ResponseDTO<Void>> createHoliday(@RequestBody HolidaysDTO holidaysDTO){
        holidayService.createHoliday(holidaysDTO);
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.Holiday.HOLIDAY_CREATED));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidaysDTO>>> fetchAllHolidays(){
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.Holiday.HOLIDAY_LIST_FOUND, holidayService.getDTOList(holidayService.getAllHolidays())));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidaysDTO>>> fetchHolidayByHolidayId(@PathVariable("id") Long holidayId){
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.Holiday.HOLIDAY_FOUND, holidayService.getDTOList(holidayService.getHolidayByHolidayId(holidayId))));
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidaysDTO>>> fetchAllHolidaysByHolidayName(@RequestParam("holidayName") String holidayName){
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.Holiday.HOLIDAY_LIST_FOUND, holidayService.getDTOList(holidayService.getHolidayByHolidayName(holidayName))));
    }

    @GetMapping("/date")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidaysDTO>>> fetchHolidayByHolidayDate(@RequestParam("holidayDate") String holidayDate){
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.Holiday.HOLIDAY_FOUND, holidayService.getDTOList(holidayService.getHolidayByHolidayDate(holidayDate))));
    }

    @GetMapping("/type")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<HolidaysDTO>>> fetchAllHolidaysByHolidayType(@RequestParam("holidayType") String label){
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.Holiday.HOLIDAY_LIST_FOUND, holidayService.getDTOList(holidayService.getAllHolidaysByHolidayType(label))));
    }
}