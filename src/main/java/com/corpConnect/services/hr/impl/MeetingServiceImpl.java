package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.MeetingDTO;
import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.entities.hr.Meeting;
import com.corpConnect.mappers.hr.MeetingMapper;
import com.corpConnect.repositories.hr.MeetingRepository;
import com.corpConnect.services.hr.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;

    @Override
    public Meeting getEntity(MeetingDTO meetingDTO) {
        return meetingMapper.toEntity(meetingDTO);
    }

    @Override
    public MeetingDTO getDTO(Meeting meeting) {
        return meetingMapper.toDTO(meeting);
    }

    @Override
    public List<Meeting> getEntityList(List<MeetingDTO> meetingDTOList) {
        return meetingMapper.toEntityList(meetingDTOList);
    }

    @Override
    public List<MeetingDTO> getDTOList(List<Meeting> meetingList) {
        return meetingMapper.toDTOList(meetingList);
    }

    @Override
    public void createMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = meetingMapper.toEntity(meetingDTO);

    }

    @Override
    public void updateMeeting(Long meetingToUpdateId, MeetingDTO newMeetingDTO) {

    }

    @Override
    public Meeting getMeetingById(Long meetingToGetId) {
        return null;
    }

    @Override
    public void deleteMeeting(MeetingRoomDTO meetingRoomDTO) {

    }

    @Override
    public void deleteMeetingById(Long meetingToDeleteId) {

    }
}
