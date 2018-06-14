package com.hna.dbp.dao.entity;

import java.io.Serializable;

public class Plugins implements Serializable {
	/**
	 * 
	 */

	private String id; //

	private String name; //

	private String apiId; //

	private String consumerId; //

	private String config; //

	private Integer enabled; //

	private Long createOn; //

	private Integer deleteMark; //

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiId() {
		return this.apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getConsumerId() {
		return this.consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getConfig() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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

}
