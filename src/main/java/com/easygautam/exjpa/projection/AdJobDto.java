package com.easygautam.exjpa.projection;

import com.easygautam.exjpa.entity.Ad;
import com.easygautam.exjpa.entity.AdJob;
import com.easygautam.exjpa.entity.MobileApp;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdJobDto {

    private Long id;

    @Min(value = 1, message = "Day must be min 1")
    @Max(value = 7, message = "Day must be max 7")
    private int day;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date time;

    @NotNull
    private Ad ad;
    @NotNull
    private MobileApp mobileApp;
    @NotNull
    @NotEmpty
    private AdJob.Type type;
    private boolean enable;

    public static AdJobDto of(AdJob adJob) {
        return new AdJobDto(adJob);
    }

    private AdJobDto(AdJob job) {
        this.id = job.getId();
        this.day = job.getDay();
        this.time = job.getTime();
        this.ad = job.getAd();
        this.mobileApp = job.getMobileApp();
        this.type = job.getType();
        this.enable = job.isEnable();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public MobileApp getMobileApp() {
        return mobileApp;
    }

    public void setMobileApp(MobileApp mobileApp) {
        this.mobileApp = mobileApp;
    }

    public AdJob.Type getType() {
        return type;
    }

    public void setType(AdJob.Type type) {
        this.type = type;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
