package com.hna.dbp.dao.entity;

import java.io.Serializable;

public class Apis implements Serializable {

	private String id; //

	private String path; //

	private String serviceId; //

	private String url; //

	private Integer retryable; //

	private Integer enabled; //

	private Integer stripPrefix; //

	private String apiName; //

	private Long createOn; //

	private Integer deleteMark; //

	private Integer status; // 0:停用1：可用2:不可用

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRetryable() {
		return this.retryable;
	}

	public void setRetryable(Integer retryable) {
		this.retryable = retryable;
	}

	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getStripPrefix() {
		return this.stripPrefix;
	}

	public void setStripPrefix(Integer stripPrefix) {
		this.stripPrefix = stripPrefix;
	}

	public String getApiName() {
		return this.apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public Long getCreateOn() {
		return this.createOn;
	}

	public void setCreateOn(Long createOn) {
		this.createOn = createOn;
	}

	public Integer getDeleteMark() {
		return this.deleteMark;
	}

	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
