package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.Holiday;
import com.corpConnect.enumerations.HolidayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Holiday findByDate(LocalDate localDate);
    Holiday findByName(String name);
    List<Holiday> findByNameContainingIgnoreCase(String name); // for searching filter
    List<Holiday> findByType(HolidayType holidayType);

    List<Holiday> findByIsDeleted(boolean isDeleted);
    boolean existsByDate(LocalDate date);

    @Query("SELECT h FROM Holiday h WHERE MONTH(h.date) = :month AND YEAR(h.date) = :year")
    List<Holiday> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
