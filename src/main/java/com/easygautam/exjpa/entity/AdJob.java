package com.easygautam.exjpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ad_job")
public class AdJob {

    public enum Type {
        NOTIFICATION, POSTER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(value = 1, message = "Day must be min 1")
    @Max(value = 7, message = "Day must be max 7")
    @Column(name = "day")
    private int day;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "time")
    private Date time;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    private Ad ad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "mobile_app_id", referencedColumnName = "id")
    private MobileApp mobileApp;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AdJob.Type type;

    @Column(name = "enable")
    private boolean enable;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
