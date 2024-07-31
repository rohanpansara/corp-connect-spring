package com.jwtauthentication.services.hr;

import com.jwtauthentication.dtos.hr.HolidayDTO;
import com.jwtauthentication.entities.hr.Holiday;
import com.jwtauthentication.mappers.hr.HolidayMapper;
import com.jwtauthentication.repositories.hr.HolidayRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class HolidayService {

    private final HolidayMapper holidayMapper;
    private final HolidayRepository holidayRepository;

    public Holiday getEntity(HolidayDTO holidayDTO){
        return holidayMapper.toEntity(holidayDTO);
    }

    public HolidayDTO getDTO(Holiday holiday){
        return holidayMapper.toDTO(holiday);
    }

    public List<HolidayDTO> getDTOList(List<Holiday> holidayList){
        return holidayMapper.toDTOList(holidayList);
    }

    public List<Holiday> getEntityList(List<HolidayDTO> holidayDTOList){
        return holidayMapper.toEntityList(holidayDTOList);
    }

    public HolidayDTO createHoliday(Holiday holiday){
        return holidayMapper.toDTO(holidayRepository.save(holiday));
    }
}


