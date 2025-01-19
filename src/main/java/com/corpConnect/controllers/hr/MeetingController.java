package com.corpConnect.controllers.hr;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/meeting")
@PreAuthorize("hasRole('HR_MANAGER')")
public class MeetingController {



}
