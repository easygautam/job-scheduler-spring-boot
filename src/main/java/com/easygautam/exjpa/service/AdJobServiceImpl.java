package com.easygautam.exjpa.service;

import com.easygautam.exjpa.entity.AdJob;
import com.easygautam.exjpa.exception.JobAlreadyScheduledException;
import com.easygautam.exjpa.projection.AdJobDto;
import com.easygautam.exjpa.repository.AdJobRepository;
import com.easygautam.exjpa.repository.AdRepository;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdJobServiceImpl implements AdJobService {

    private AdRepository adRepository;
    private AdJobRepository adJobRepository;
    private AdJobTaskScheduler adJobTaskScheduler;

    public AdJobServiceImpl(AdRepository adRepository, AdJobRepository adJobRepository, AdJobTaskScheduler adJobTaskScheduler) {
        this.adRepository = adRepository;
        this.adJobRepository = adJobRepository;
        this.adJobTaskScheduler = adJobTaskScheduler;
    }

    @Override
    public List<AdJobDto> getAllAdJobByType(AdJob.Type type) {
        return adJobRepository
                .findAllByType(type).stream()
                .map(AdJobDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public AdJobDto createAdJob(AdJob adJob) {
        AdJob createdAdJob = adJobRepository.save(adJob);
        return AdJobDto.of(createdAdJob);
    }


    /**
     * Get all jobs from database and schedule
     */
    @EventListener(ContextRefreshedEvent.class)
    private void scheduleAllAds() {
        adJobRepository.findAll().forEach(adJob -> {
            try {
                adJobTaskScheduler.addJob(adJob);
            } catch (JobAlreadyScheduledException e) {
                e.printStackTrace();
            }
        });
    }


}
