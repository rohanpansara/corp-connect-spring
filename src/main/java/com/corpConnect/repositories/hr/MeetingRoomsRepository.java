package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.MeetingRooms;
import com.corpConnect.entities.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRoomsRepository extends JpaRepository<MeetingRooms, Long> {

    MeetingRooms findByName(String name);

    List<MeetingRooms> findByFloorNumber(Integer floorNumber);
    List<MeetingRooms> findByPointOfContact(Users pointOfContact);

    List<MeetingRooms> findByCapacityGreaterThan(Integer capacity);
    List<MeetingRooms> findByEquipmentContaining(String equipmentName);

}

