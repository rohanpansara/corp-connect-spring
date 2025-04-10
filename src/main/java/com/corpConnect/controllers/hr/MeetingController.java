package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.dtos.hr.MeetingDTO;
import com.corpConnect.services.hr.MeetingService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/meeting")
@PreAuthorize("hasRole('HR_MANAGER')")
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<MeetingDTO>>> fetchAllMeetings() {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Meeting.MEETING_LIST_FOUND, meetingService.getDTOList(meetingService.getAllMeetings())));
    }

    @GetMapping(value = "/{meetingId}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<MeetingDTO>>> fetchMeetingByMeetingId(@PathVariable("meetingId") Long meetingId) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Meeting.MEETING_LIST_FOUND, meetingService.getDTOList(meetingService.getMeetingsById(meetingId))));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('pms_manager:create')")
    public ResponseEntity<ResponseDTO<Void>> createMeeting(@RequestBody MeetingDTO meetingDTO) {
        meetingService.createMeeting(meetingDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Meeting.MEETING_CREATED));
    }

    @PutMapping(value = "/{meetingId}")
    @PreAuthorize("hasAuthority('hr_manager:update')")
    public ResponseEntity<ResponseDTO<Void>> updateMeeting(@PathVariable("meetingId") Long meetingId, @RequestBody MeetingDTO meetingDTO) {
        meetingService.updateMeeting(meetingId, meetingDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Meeting.MEETING_UPDATED));
    }

    @DeleteMapping(value = "/{meetingId}")
    @PreAuthorize("hasAuthority('hr_manager:delete')")
    public ResponseEntity<ResponseDTO<Void>> softDeleteMeeting(@PathVariable("meetingId") Long meetingId) {
        meetingService.deleteMeetingById(meetingId);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Meeting.MEETING_DELETED));
    }

    @DeleteMapping(value = "/{meetingId}/permanent")
    @PreAuthorize("hasAuthority('hr_manager:delete')")
    public ResponseEntity<ResponseDTO<Void>> permanentDeleteJobTitle(@PathVariable("meetingId") Long meetingId) {
        meetingService.deleteMeetingById(meetingId);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Meeting.MEETING_DELETED));
    }

}
