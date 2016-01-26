package com.myplace.dto;

import java.util.Date;
import java.util.List;

public class UserInfo {

	private long id;
	private String contactName;
	private String firstName;
	private String lastName;
	private String userName;
	private String businessName;
	private String salutation;
	private Date dob;
	private int gender;
	private String contactNumber;
	private String contactAddressLine1;
	private String contactAddressLine2;
	private String zipcode;
	private String city;
	private String state;
	private String country;
	private String secondaryEmailAddress;	
	private String timeZone;
	private boolean isRegister; // this will tell user is newly register or login
	private int status;
	private String language;
	private String location;
	private String userDescription;
	private String webSite;
	private String profileUpdateUrl;
	private String bussListUrl;
	private String changePassUrl;
	private String verifyAccUrl;
	private String address;
	private String primaryEmailAddress;
	private float longitude;
	private float latitude; 
	private Date modifiedDate;
	List <String> imgUrls;
	List<UserFileInfo>  userFileInfo;
	private int bussCnt;  // give business count of users
	
	public String getBussListUrl() {
		return bussListUrl;
	}
	public void setBussListUrl(String bussListUrl) {
		this.bussListUrl = bussListUrl;
	}
	public String getChangePassUrl() {
		return changePassUrl;
	}
	public void setChangePassUrl(String changePassUrl) {
		this.changePassUrl = changePassUrl;
	}
	public String getVerifyAccUrl() {
		return verifyAccUrl;
	}
	public void setVerifyAccUrl(String verifyAccUrl) {
		this.verifyAccUrl = verifyAccUrl;
	}
	public int getBussCnt() {
		return bussCnt;
	}
	public void setBussCnt(int bussCnt) {
		this.bussCnt = bussCnt;
	}
	public boolean isRegister() {
		return isRegister;
	}
	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}
	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}
	
	public List<UserFileInfo> getUserFileInfo() {
		return userFileInfo;
	}
	public void setUserFileInfo(List<UserFileInfo> userFileInfo) {
		this.userFileInfo = userFileInfo;
	}
	

	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactAddressLine1() {
		return contactAddressLine1;
	}
	public void setContactAddressLine1(String contactAddressLine1) {
		this.contactAddressLine1 = contactAddressLine1;
	}
	public String getContactAddressLine2() {
		return contactAddressLine2;
	}
	public void setContactAddressLine2(String contactAddressLine2) {
		this.contactAddressLine2 = contactAddressLine2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSecondaryEmailAddress() {
		return secondaryEmailAddress;
	}
	public void setSecondaryEmailAddress(String secondaryEmailAddress) {
		this.secondaryEmailAddress = secondaryEmailAddress;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getProfileUpdateUrl() {
		return profileUpdateUrl;
	}
	public void setProfileUpdateUrl(String profileUpdateUrl) {
		this.profileUpdateUrl = profileUpdateUrl;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrimaryEmailAddress() {
		return primaryEmailAddress;
	}
	public void setPrimaryEmailAddress(String primaryEmailAddress) {
		this.primaryEmailAddress = primaryEmailAddress;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
