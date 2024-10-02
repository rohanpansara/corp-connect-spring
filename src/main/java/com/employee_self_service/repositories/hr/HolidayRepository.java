package com.employee_self_service.repositories.hr;

import com.employee_self_service.entities.hr.Holidays;
import com.employee_self_service.enumerations.HolidayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holidays, Long> {
    Holidays findByDate(LocalDate localDate);
    Holidays findByName(String name);
    List<Holidays> findByNameContainingIgnoreCase(String name); // for searching filter
    List<Holidays> findByType(HolidayType holidayType);

    @Query("SELECT h FROM Holidays h WHERE MONTH(h.date) = :month AND YEAR(h.date) = :year")
    List<Holidays> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
