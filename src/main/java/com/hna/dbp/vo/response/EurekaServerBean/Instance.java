package com.hna.dbp.vo.response.EurekaServerBean;


import java.io.Serializable;

//实例表
public class Instance implements Serializable {

     //实例id
     private String instanceId;

     //主机名
     private String hostName;

     //应用名
     private String app;

     //ip地址
     private String ipAddr;

     //状态
     private String status;


     private String overriddenstatus;

     //端口
     private Ports port;

     //安全端口
     private SecurePort securePort;

     //
     private int countryId;

     //数据中心表
    private DataCenterInfo dataCenterInfo;

    //租约信息表
    private LeaseInfo leaseInfo;

    private Metadatas metadata;

    private String homePageUrl;

    private String statusPageUrl;

    private String healthCheckUrl;

    private String vipAddress;

    private String secureVipAddress;

    private String isCoordinatingDiscoveryServer;

    private String lastUpdatedTimestamp;

    private String astDirtyTimestamp;

    private String actionType;


 public String getInstanceId() {
  return instanceId;
 }

 public void setInstanceId(String instanceId) {
  this.instanceId = instanceId;
 }

 public String getHostName() {
  return hostName;
 }

 public void setHostName(String hostName) {
  this.hostName = hostName;
 }

 public String getApp() {
  return app;
 }

 public void setApp(String app) {
  this.app = app;
 }

 public String getIpAddr() {
  return ipAddr;
 }

 public void setIpAddr(String ipAddr) {
  this.ipAddr = ipAddr;
 }

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public String getOverriddenstatus() {
  return overriddenstatus;
 }

 public void setOverriddenstatus(String overriddenstatus) {
  this.overriddenstatus = overriddenstatus;
 }

 public Ports getPort() {
  return port;
 }

 public void setPort(Ports port) {
  this.port = port;
 }

 public SecurePort getSecurePort() {
  return securePort;
 }

 public void setSecurePort(SecurePort securePort) {
  this.securePort = securePort;
 }

 public int getCountryId() {
  return countryId;
 }

 public void setCountryId(int countryId) {
  this.countryId = countryId;
 }

 public DataCenterInfo getDataCenterInfo() {
  return dataCenterInfo;
 }

 public void setDataCenterInfo(DataCenterInfo dataCenterInfo) {
  this.dataCenterInfo = dataCenterInfo;
 }

 public LeaseInfo getLeaseInfo() {
  return leaseInfo;
 }

 public void setLeaseInfo(LeaseInfo leaseInfo) {
  this.leaseInfo = leaseInfo;
 }

 public Metadatas getMetadata() {
  return metadata;
 }

 public void setMetadata(Metadatas metadata) {
  this.metadata = metadata;
 }

 public String getHomePageUrl() {
  return homePageUrl;
 }

 public void setHomePageUrl(String homePageUrl) {
  this.homePageUrl = homePageUrl;
 }

 public String getStatusPageUrl() {
  return statusPageUrl;
 }

 public void setStatusPageUrl(String statusPageUrl) {
  this.statusPageUrl = statusPageUrl;
 }

 public String getHealthCheckUrl() {
  return healthCheckUrl;
 }

 public void setHealthCheckUrl(String healthCheckUrl) {
  this.healthCheckUrl = healthCheckUrl;
 }

 public String getVipAddress() {
  return vipAddress;
 }

 public void setVipAddress(String vipAddress) {
  this.vipAddress = vipAddress;
 }

 public String getSecureVipAddress() {
  return secureVipAddress;
 }

 public void setSecureVipAddress(String secureVipAddress) {
  this.secureVipAddress = secureVipAddress;
 }

 public String getIsCoordinatingDiscoveryServer() {
  return isCoordinatingDiscoveryServer;
 }

 public void setIsCoordinatingDiscoveryServer(String isCoordinatingDiscoveryServer) {
  this.isCoordinatingDiscoveryServer = isCoordinatingDiscoveryServer;
 }

 public String getLastUpdatedTimestamp() {
  return lastUpdatedTimestamp;
 }

 public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
  this.lastUpdatedTimestamp = lastUpdatedTimestamp;
 }

 public String getAstDirtyTimestamp() {
  return astDirtyTimestamp;
 }

 public void setAstDirtyTimestamp(String astDirtyTimestamp) {
  this.astDirtyTimestamp = astDirtyTimestamp;
 }

 public String getActionType() {
  return actionType;
 }

 public void setActionType(String actionType) {
  this.actionType = actionType;
 }

 @Override
 public String toString() {
  return "Instance{" +
          ", instanceId='" + instanceId + '\'' +
          ", hostName='" + hostName + '\'' +
          ", app='" + app + '\'' +
          ", ipAddr='" + ipAddr + '\'' +
          ", status='" + status + '\'' +
          ", overriddenstatus='" + overriddenstatus + '\'' +
          ", port=" + port +
          ", securePort=" + securePort +
          ", countryId=" + countryId +
          ", dataCenterInfo=" + dataCenterInfo +
          ", leaseInfo=" + leaseInfo +
          ", metadata=" + metadata +
          ", homePageUrl='" + homePageUrl + '\'' +
          ", statusPageUrl='" + statusPageUrl + '\'' +
          ", healthCheckUrl='" + healthCheckUrl + '\'' +
          ", vipAddress='" + vipAddress + '\'' +
          ", secureVipAddress='" + secureVipAddress + '\'' +
          ", isCoordinatingDiscoveryServer='" + isCoordinatingDiscoveryServer + '\'' +
          ", lastUpdatedTimestamp='" + lastUpdatedTimestamp + '\'' +
          ", astDirtyTimestamp='" + astDirtyTimestamp + '\'' +
          ", actionType='" + actionType + '\'' +
          '}';
 }
}
