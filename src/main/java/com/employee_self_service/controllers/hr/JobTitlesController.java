package com.employee_self_service.controllers.hr;

import com.employee_self_service.dtos.common.ResponseDTO;
import com.employee_self_service.dtos.hr.JobTitlesDTO;
import com.employee_self_service.services.hr.JobTitlesService;
import com.employee_self_service.utils.EssConstants;
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
public class JobTitlesController {

    private final JobTitlesService jobTitlesService;

    @GetMapping
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<JobTitlesDTO>>> fetchAllJobTitles() {
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.JobTitles.JOB_TITLE_LIST_FOUND, jobTitlesService.getDTOList(jobTitlesService.getAllJobTitles())));
    }

    @GetMapping(value = "/{job-title-id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<JobTitlesDTO>>> fetchJobTitleByJobTitleId(@PathVariable("job-title-id") Long jobTitleId) {
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.JobTitles.JOB_TITLE_FOUND, jobTitlesService.getDTOList(jobTitlesService.getJobTitlesById(jobTitleId))));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hr_admin:create')")
    public ResponseEntity<ResponseDTO<Void>> createJobTitle(@RequestBody JobTitlesDTO jobTitlesDTO) {
        jobTitlesService.createJobTitles(jobTitlesDTO);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.JobTitles.JOB_TITLE_CREATED));
    }

    @PutMapping(value = "/{job-title-id}")
    @PreAuthorize("hasAuthority('hr_manager:update')")
    public ResponseEntity<ResponseDTO<Void>> updateJobTitle(@PathVariable("job-title-id") Long oldJobTitleId, @RequestBody JobTitlesDTO jobTitlesDTO) {
        jobTitlesService.updateJobTitles(oldJobTitleId, jobTitlesDTO);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.JobTitles.JOB_TITLE_UPDATED));
    }

    @DeleteMapping(value = "/{job-title-id}")
    @PreAuthorize("hasAuthority('hr_admin:delete')")
    public ResponseEntity<ResponseDTO<Void>> softDeleteJobTitle(@PathVariable("job-title-id") Long jobTitleId) {
        jobTitlesService.deleteJobTitlesById(jobTitleId, false);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.JobTitles.JOB_TITLE_DELETED));
    }

    @DeleteMapping(value = "/{job-title-id}/permanent")
    @PreAuthorize("hasAuthority('hr_admin:delete')")
    public ResponseEntity<ResponseDTO<Void>> permanentDeleteJobTitle(@PathVariable("job-title-id") Long jobTitleId) {
        jobTitlesService.deleteJobTitlesById(jobTitleId, true);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.JobTitles.JOB_TITLE_DELETED_PERMANENTLY));
    }

}
