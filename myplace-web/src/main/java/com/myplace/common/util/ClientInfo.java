package com.myplace.common.util;

public class ClientInfo {
	private String version ;
	private String versionCode;
	private String deviceId;
	private String userId;
	private String platform;
	private String tag;
	private String manufacturer;
	private String model;
	private String imei;
	private String wdthpixels;
	private String hghtpixels;
	private String operatorsimname;
	private String seviceprovider ;
    FindOnLocation findOnLocation;
   
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getWdthpixels() {
		return wdthpixels;
	}
	public void setWdthpixels(String wdthpixels) {
		this.wdthpixels = wdthpixels;
	}
	public String getHghtpixels() {
		return hghtpixels;
	}
	public void setHghtpixels(String hghtpixels) {
		this.hghtpixels = hghtpixels;
	}
	public String getOperatorsimname() {
		return operatorsimname;
	}
	public void setOperatorsimname(String operatorsimname) {
		this.operatorsimname = operatorsimname;
	}
	public String getSeviceprovider() {
		return seviceprovider;
	}
	public void setSeviceprovider(String seviceprovider) {
		this.seviceprovider = seviceprovider;
	}
	
    public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public FindOnLocation getFindOnLocation() {
		return findOnLocation;
	}
	public void setFindOnLocation(FindOnLocation findOnLocation) {
		this.findOnLocation = findOnLocation;
	}
	
}
