package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.MeetingDTO;
import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.entities.hr.Meeting;

import java.util.List;

public interface MeetingService {

    // Meeting Mapper
    Meeting getEntity(MeetingDTO meetingDTO);
    MeetingDTO getDTO(Meeting meeting);
    List<Meeting> getEntityList(List<MeetingDTO> meetingDTOList);
    List<MeetingDTO> getDTOList(List<Meeting> meetingList);

    // CRUD Operations
    void createMeeting(MeetingDTO meetingDTO);
    void updateMeeting(Long meetingToUpdateId, MeetingDTO newMeetingDTO);
    Meeting getMeetingById(Long meetingToGetId);
    void deleteMeeting(MeetingRoomDTO meetingRoomDTO);
    void deleteMeetingById(Long meetingToDeleteId);

}
