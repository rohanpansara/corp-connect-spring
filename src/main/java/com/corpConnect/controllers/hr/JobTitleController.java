package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.services.hr.JobTitleService;
import com.corpConnect.utils.constants.CorpConnectConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/hr/job-titles")
@PreAuthorize("hasRole('HR_ADMIN')")
public class JobTitleController {

    private final JobTitleService jobTitleService;

    @GetMapping
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<JobTitleDTO>>> fetchAllJobTitles() {
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.JobTitles.JOB_TITLE_LIST_FOUND, jobTitleService.getDTOList(jobTitleService.getAllJobTitles())));
    }

    @GetMapping(value = "/{job-title-id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<JobTitleDTO>>> fetchJobTitleByJobTitleId(@PathVariable("job-title-id") Long jobTitleId) {
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.JobTitles.JOB_TITLE_FOUND, jobTitleService.getDTOList(jobTitleService.getJobTitlesById(jobTitleId))));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hr_admin:create')")
    public ResponseEntity<ResponseDTO<Void>> createJobTitle(@RequestBody JobTitleDTO jobTitleDTO) {
        jobTitleService.createJobTitles(jobTitleDTO);
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.JobTitles.JOB_TITLE_CREATED));
    }

    @PutMapping(value = "/{job-title-id}")
    @PreAuthorize("hasAuthority('hr_manager:update')")
    public ResponseEntity<ResponseDTO<Void>> updateJobTitle(@PathVariable("job-title-id") Long oldJobTitleId, @RequestBody JobTitleDTO jobTitleDTO) {
        jobTitleService.updateJobTitles(oldJobTitleId, jobTitleDTO);
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.JobTitles.JOB_TITLE_UPDATED));
    }

    @DeleteMapping(value = "/{job-title-id}")
    @PreAuthorize("hasAuthority('hr_admin:delete')")
    public ResponseEntity<ResponseDTO<Void>> softDeleteJobTitle(@PathVariable("job-title-id") Long jobTitleId) {
        jobTitleService.deleteJobTitlesById(jobTitleId, false);
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.JobTitles.JOB_TITLE_DELETED));
    }

    @DeleteMapping(value = "/{job-title-id}/permanent")
    @PreAuthorize("hasAuthority('hr_admin:delete')")
    public ResponseEntity<ResponseDTO<Void>> permanentDeleteJobTitle(@PathVariable("job-title-id") Long jobTitleId) {
        jobTitleService.deleteJobTitlesById(jobTitleId, true);
        return ResponseEntity.ok(ResponseDTO.success(CorpConnectConstants.JobTitles.JOB_TITLE_DELETED_PERMANENTLY));
    }

}