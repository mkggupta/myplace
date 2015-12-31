package com.myplace.dao.entities;

import java.util.Date;

public class UserPushInfo {
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPushKey() {
		return pushKey;
	}
	public void setPushKey(String pushKey) {
		this.pushKey = pushKey;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Byte getPlatform() {
		return platform;
	}
	public void setPlatform(Byte platform) {
		this.platform = platform;
	}

	private Long userId;
	private String pushKey;
	private String deviceId;
	private Boolean status;
	private Date modifiedDate;
	private Date createdDate;
	private Byte platform;
	

}
