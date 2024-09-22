package com.employee_self_service.repositories.hr;

import com.employee_self_service.entities.hr.MeetingRooms;
import com.employee_self_service.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRoomsRepository extends JpaRepository<MeetingRooms, Long> {

    MeetingRooms findByName(String name);

    List<MeetingRooms> findByFloorNumber(Integer floorNumber);
    List<MeetingRooms> findByPointOfContact(User pointOfContact);

    List<MeetingRooms> findByCapacityGreaterThan(Integer capacity);
    List<MeetingRooms> findByEquipmentContaining(String equipmentName);

}

