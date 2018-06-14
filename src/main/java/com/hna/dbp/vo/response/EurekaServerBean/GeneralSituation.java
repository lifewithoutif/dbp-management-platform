package com.hna.dbp.vo.response.EurekaServerBean;


import java.io.Serializable;

/**
 * 概况
 *
 * @author skj
 * @create 2018-02-27
 **/
public class GeneralSituation implements Serializable {

    //服务器名
    private String serverName;

    //服务ip
    private String serverIp;

    //状态
    private String status;

    //上线时间
    private String onlineTime;

    //可用内存
    private int availableMemory;

    //环境
    private String environment;

    //期望每分钟心跳
    private int expectHeartbeat;

    //上一分钟实际心跳
    private int realityHeartbeat;

    //异常服务器数
    private int serverAbnormal;

    //服务器总数
    private int serverTotal;

    // 异常服务总数
    private int serviceAbnoarmal;

    //服务总数
    private int serviceTotal;

    //异常实例总数
    private int instanceAbnormal;

    //实例总数
    private int instanceTotal;

    @Override
    public String toString() {
        return "GeneralSituation{" +
                "serverName='" + serverName + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", status='" + status + '\'' +
                ", onlineTime='" + onlineTime + '\'' +
                ", availableMemory=" + availableMemory +
                ", environment='" + environment + '\'' +
                ", expectHeartbeat=" + expectHeartbeat +
                ", realityHeartbeat=" + realityHeartbeat +
                ", serverAbnormal=" + serverAbnormal +
                ", serverTotal=" + serverTotal +
                ", serviceAbnoarmal=" + serviceAbnoarmal +
                ", serviceTotal=" + serviceTotal +
                ", instanceAbnormal=" + instanceAbnormal +
                ", instanceTotal=" + instanceTotal +
                '}';
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public int getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(int availableMemory) {
        this.availableMemory = availableMemory;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getExpectHeartbeat() {
        return expectHeartbeat;
    }

    public void setExpectHeartbeat(int expectHeartbeat) {
        this.expectHeartbeat = expectHeartbeat;
    }

    public int getRealityHeartbeat() {
        return realityHeartbeat;
    }

    public void setRealityHeartbeat(int realityHeartbeat) {
        this.realityHeartbeat = realityHeartbeat;
    }

    public int getServerAbnormal() {
        return serverAbnormal;
    }

    public void setServerAbnormal(int serverAbnormal) {
        this.serverAbnormal = serverAbnormal;
    }

    public int getServerTotal() {
        return serverTotal;
    }

    public void setServerTotal(int serverTotal) {
        this.serverTotal = serverTotal;
    }

    public int getServiceAbnoarmal() {
        return serviceAbnoarmal;
    }

    public void setServiceAbnoarmal(int serviceAbnoarmal) {
        this.serviceAbnoarmal = serviceAbnoarmal;
    }

    public int getServiceTotal() {
        return serviceTotal;
    }

    public void setServiceTotal(int serviceTotal) {
        this.serviceTotal = serviceTotal;
    }

    public int getInstanceAbnormal() {
        return instanceAbnormal;
    }

    public void setInstanceAbnormal(int instanceAbnormal) {
        this.instanceAbnormal = instanceAbnormal;
    }

    public int getInstanceTotal() {
        return instanceTotal;
    }

    public void setInstanceTotal(int instanceTotal) {
        this.instanceTotal = instanceTotal;
    }
}
