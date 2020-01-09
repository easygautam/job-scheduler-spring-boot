package com.easygautam.exjpa.service;

import com.easygautam.exjpa.entity.AdJob;
import com.easygautam.exjpa.exception.JobAlreadyScheduledException;
import com.easygautam.exjpa.exception.JobNotFondException;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class AdJobTaskScheduler {

    private TaskScheduler taskScheduler;
    private Map<Long, ScheduledFuture<?>> scheduledJobs = new HashMap<>();

    public AdJobTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public void addJob(AdJob adJob) throws JobAlreadyScheduledException {
        // Check job id already present
        ScheduledFuture<?> scheduledFuture = scheduledJobs.get(adJob.getId());
        if (scheduledFuture != null) throw new JobAlreadyScheduledException();
        // This task will be trigger in 5 second
        System.out.println("########## New Ad Job Added Id: " + adJob.getId() + " #########");
        LocalTime time = LocalTime.parse(adJob.getTime().toString());
        String croneJobExpression = time.getSecond() + " " + time.getMinute() + " " + time.getHour() + " * * " + (adJob.getDay() - 1);
//        String croneJobExpression = time.getSecond() + " * * * * *";
        CronTrigger trigger = new CronTrigger(croneJobExpression);
        scheduledFuture = taskScheduler.schedule(() -> {
            System.out.println("Job  in progress: " + adJob.getId());
        }, trigger);
        scheduledJobs.put(adJob.getId(), scheduledFuture);
    }

    public void cancelJob(AdJob adJob) throws JobNotFondException {
        ScheduledFuture<?> scheduledFuture = scheduledJobs.get(adJob.getId());
        if (scheduledFuture == null) throw new JobNotFondException();
        System.out.println("########## Job Canceled Id: " + adJob.getId() + " #########");
        scheduledFuture.cancel(true);
        scheduledJobs.remove(adJob.getId());
    }

}
