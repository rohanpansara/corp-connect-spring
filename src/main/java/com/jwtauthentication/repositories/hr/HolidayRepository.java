package com.jwtauthentication.repositories.hr;

import com.jwtauthentication.entities.hr.Holiday;
import com.jwtauthentication.enumerations.HolidayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Holiday findByDate(LocalDate localDate);
    Holiday findByName(String name);
    List<Holiday> findByType(HolidayType holidayType);
}
