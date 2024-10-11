package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.entities.hr.MeetingRoom;

import java.util.List;

public interface MeetingRoomService {

    MeetingRoom getEntity(MeetingRoomDTO meetingRoomDTO);
    MeetingRoomDTO getDTO(MeetingRoom meetingRoom);
    List<MeetingRoom> getEntityList(List<MeetingRoomDTO> meetingRoomDTOList);
    List<MeetingRoomDTO> getDTOList(List<MeetingRoom> meetingRoomList);

    void createMeetingRoom(MeetingRoomDTO meetingRoomDTO);
    void updateMeetingRoom(Long oldMeetingRoomId, MeetingRoomDTO meetingRoomDTO);
    void deleteMeetingRoom(MeetingRoomDTO meetingRoomDTO, Boolean isPermanentDelete);
    void deleteMeetingRoomById(Long meetingRoomId, Boolean isPermanentDelete);

    List<MeetingRoom> getAllMeetingRooms(Boolean isDeleted);
    List<MeetingRoom> getAllNonDeletedMeetingRooms();
    List<MeetingRoom> getAllDeletedMeetingRooms();
    List<MeetingRoom> getMeetingRoomById(Long meetingRoomId);
    List<MeetingRoom> getMeetingRoomByName(String meetingRoomName);
    
}
