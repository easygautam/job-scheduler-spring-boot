package com.easygautam.exjpa.controller;

import com.easygautam.exjpa.entity.AdJob;
import com.easygautam.exjpa.exception.JobAlreadyScheduledException;
import com.easygautam.exjpa.projection.AdJobDto;
import com.easygautam.exjpa.service.AdJobService;
import com.easygautam.exjpa.service.AdJobTaskScheduler;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ad/job")
public class AdJobController {

    private AdJobTaskScheduler adJobTaskScheduler;
    private AdJobService adJobService;

    public AdJobController(AdJobTaskScheduler adJobTaskScheduler, AdJobService adJobService) {
        this.adJobTaskScheduler = adJobTaskScheduler;
        this.adJobService = adJobService;
    }

    @PostMapping()
    ResponseEntity<AdJobDto> createAdJob(@RequestBody AdJob adJob) {
        return ResponseEntity.ok(adJobService.createAdJob(adJob));
    }

    @GetMapping()
    ResponseEntity<List<AdJobDto>> getAllAdJobs(@RequestParam("type") AdJob.Type type) {
        return ResponseEntity.ok(adJobService.getAllAdJobByType(type));
    }

    @GetMapping("/schedule")
    ResponseEntity<Map<String, Object>> scheduleJobs(@RequestParam("job_ids") Integer[] jobIds) {
        ArrayList<Integer> successJobIds = new ArrayList<>();
        ArrayList<Map<String, Object>> failedJobIds = new ArrayList<>();
        for (Integer jobId : jobIds) {
//            try {
//                adJobTaskScheduler.addJob((long) jobId);
//                successJobIds.add(jobId);
//            } catch (JobAlreadyScheduledException e) {
//                Map<String, Object> failed = new HashMap<>();
//                failed.put("id", jobId);
//                failed.put("message", "Job already scheduled");
//                failedJobIds.add(failed);
//            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success_jobs", successJobIds);
        response.put("failed_jobs", failedJobIds);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancel")
    ResponseEntity<Map<String, Object>> cancelJobs(@RequestParam("job_ids") Integer[] jobIds) {
        ArrayList<Integer> successJobIds = new ArrayList<>();
        ArrayList<Map<String, Object>> failedJobIds = new ArrayList<>();
        for (Integer jobId : jobIds) {
//            try {
//                adJobTaskScheduler.cancelJob((long) jobId);
//                successJobIds.add(jobId);
//            } catch ( e) {
//                Map<String, Object> failed = new HashMap<>();
//                failed.put("id", jobId);
//                failed.put("message", "Job already canceled");
//                failedJobIds.add(failed);
//            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success_jobs", successJobIds);
        response.put("failed_jobs", failedJobIds);
        return ResponseEntity.ok(response);
    }

}
