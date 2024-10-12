package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.dtos.hr.WorkShiftDTO;
import com.corpConnect.services.hr.MeetingRoomService;
import com.corpConnect.utils.constants.MessageConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/meeting-room")
@PreAuthorize("hasRole('HR_MANAGER')")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    @GetMapping
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<MeetingRoomDTO>>> fetchAllMeetingRooms(@RequestParam(required = false, value = "deleted") Boolean isDeleted){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.MeetingRoom.MEETING_ROOM_LIST_FOUND, meetingRoomService.getDTOList(meetingRoomService.getAllMeetingRooms(isDeleted))));
    }

    @GetMapping(value = "/{meeting-room-id}")
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<MeetingRoomDTO>>> fetchMeetingRoomByMeetingRoomId(@PathVariable("meeting-room-id") Long meetingRoomId){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.MeetingRoom.MEETING_ROOM_FOUND, meetingRoomService.getDTOList(meetingRoomService.getMeetingRoomById(meetingRoomId))));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hr_admin:create')")
    public ResponseEntity<ResponseDTO<Void>> createWorkShift(@Valid @RequestBody MeetingRoomDTO meetingRoomDTO) {
        meetingRoomService.createMeetingRoom(meetingRoomDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.MeetingRoom.MEETING_ROOM_CREATED));
    }

}
