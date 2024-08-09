package com.jwtauthentication.services.hr;

import com.jwtauthentication.dtos.hr.HolidayDTO;
import com.jwtauthentication.entities.hr.Holiday;
import com.jwtauthentication.enumerations.HolidayType;
import com.jwtauthentication.exceptions.hr.HolidayNotFoundException;
import com.jwtauthentication.mappers.hr.HolidayMapper;
import com.jwtauthentication.repositories.hr.HolidayRepository;
import com.jwtauthentication.utils.CustomDateTimeFormatter;
import com.jwtauthentication.utils.EssConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class HolidayService {

    private final HolidayMapper holidayMapper;
    private final HolidayRepository holidayRepository;

    public Holiday getEntity(HolidayDTO holidayDTO) {
        return holidayMapper.toEntity(holidayDTO);
    }

    public HolidayDTO getDTO(Holiday holiday) {
        return holidayMapper.toDTO(holiday);
    }

    public List<HolidayDTO> getDTOList(List<Holiday> holidayList) {
        return holidayMapper.toDTOList(holidayList);
    }

    public List<Holiday> getEntityList(List<HolidayDTO> holidayDTOList) {
        return holidayMapper.toEntityList(holidayDTOList);
    }

    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    public List<Holiday> getAllHolidaysByMonthAndYear(Integer month, Integer year){
        return holidayRepository.findByMonthAndYear(month, year);
    }

    public void createHoliday(HolidayDTO holidayDTO) {
        holidayRepository.save(this.getEntity(holidayDTO));
    }

    public List<Holiday> getHolidayByHolidayId(Long holidayId){
        return Collections.singletonList(holidayRepository.findById(holidayId).orElseThrow(
                () -> new HolidayNotFoundException(EssConstants.Holiday.HOLIDAY_NOT_FOUND)
        ));
    }

    public List<Holiday> getHolidayByHolidayName(String holidayName){
        return holidayRepository.findByNameContainingIgnoreCase(holidayName);
    }

    public List<Holiday> getHolidayByHolidayDate(String holidayDate){
        return Collections.singletonList(holidayRepository.findByDate(CustomDateTimeFormatter.getLocalDateObject(holidayDate)));
    }

    public List<Holiday> getAllHolidaysByHolidayType(String label) {
        HolidayType type = HolidayType.getByLabel(label);
        return holidayRepository.findByType(type);
    }
}


