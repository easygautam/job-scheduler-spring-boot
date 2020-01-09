package com.easygautam.exjpa.service;

import com.easygautam.exjpa.entity.AdJob;
import com.easygautam.exjpa.projection.AdJobDto;

import java.util.List;

public interface AdJobService {

    List<AdJobDto> getAllAdJobByType(AdJob.Type type);

    AdJobDto createAdJob(AdJob adJob);
}
