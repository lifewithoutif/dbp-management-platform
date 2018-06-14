package com.hna.dbp.vo.response.EurekaServerBean;

import java.io.Serializable;


//租约信息表
public class LeaseInfo implements Serializable {


    //更新间隔秒
    private int renewalIntervalInSecs;

    //持续时间秒
    private int durationInSecs;

    //注册时间戳
    private long registrationTimestamp;

    //最后更新时间戳
    private long lastRenewalTimestamp;

    //逐出；赶出；收回时间戳
    private long evictionTimestamp;

    //服务启动时间戳
    private String serviceUpTimestamp;

    public int getRenewalIntervalInSecs() {
        return renewalIntervalInSecs;
    }

    public void setRenewalIntervalInSecs(int renewalIntervalInSecs) {
        this.renewalIntervalInSecs = renewalIntervalInSecs;
    }

    public int getDurationInSecs() {
        return durationInSecs;
    }

    public void setDurationInSecs(int durationInSecs) {
        this.durationInSecs = durationInSecs;
    }

    public long getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(long registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    public long getLastRenewalTimestamp() {
        return lastRenewalTimestamp;
    }

    public void setLastRenewalTimestamp(long lastRenewalTimestamp) {
        this.lastRenewalTimestamp = lastRenewalTimestamp;
    }

    public long getEvictionTimestamp() {
        return evictionTimestamp;
    }

    public void setEvictionTimestamp(long evictionTimestamp) {
        this.evictionTimestamp = evictionTimestamp;
    }

    public String getServiceUpTimestamp() {
        return serviceUpTimestamp;
    }

    public void setServiceUpTimestamp(String serviceUpTimestamp) {
        this.serviceUpTimestamp = serviceUpTimestamp;
    }

    @Override
    public String toString() {
        return "LeaseInfo{" +
                "renewalIntervalInSecs=" + renewalIntervalInSecs +
                ", durationInSecs=" + durationInSecs +
                ", registrationTimestamp=" + registrationTimestamp +
                ", lastRenewalTimestamp=" + lastRenewalTimestamp +
                ", evictionTimestamp=" + evictionTimestamp +
                ", serviceUpTimestamp='" + serviceUpTimestamp + '\'' +
                '}';
    }
}
