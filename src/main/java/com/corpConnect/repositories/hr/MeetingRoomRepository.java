package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.MeetingRoom;
import com.corpConnect.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {

    MeetingRoom findByName(String name);

    List<MeetingRoom> findByFloorNumber(Integer floorNumber);
    List<MeetingRoom> findByPointOfContact(User pointOfContact);

    List<MeetingRoom> findByCapacityGreaterThan(Integer capacity);
    List<MeetingRoom> findByEquipmentContaining(String equipmentName);

}

