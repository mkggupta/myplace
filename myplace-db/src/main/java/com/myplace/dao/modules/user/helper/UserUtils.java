package com.myplace.dao.modules.user.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dto.UserInfo;





public class UserUtils {
	static Logger logger = LoggerFactory.getLogger(UserUtils.class);
	
	public static void updateDBUserVO(UserInfo userInfoObj, UserInfo userInfo) {
		if (null != userInfoObj && null != userInfoObj) {
			if (null != userInfo.getFirstName()) {
				userInfoObj.setFirstName(userInfo.getFirstName());
			}
			if (null != userInfo.getLastName()) {
				userInfoObj.setLastName(userInfo.getLastName());
			}
			if (null != userInfo.getCity()) {
				userInfoObj.setCity(userInfo.getCity());
			}
			if (null != userInfo.getContactAddressLine1()) {
				userInfoObj.setContactAddressLine1(userInfo.getContactAddressLine1());
			}
			if (null != userInfo.getContactAddressLine2()) {
				userInfoObj.setContactAddressLine2(userInfo.getContactAddressLine2());
			}
			if (null != userInfo.getContactNumber()) {
				userInfoObj.setContactNumber(userInfo.getContactNumber());
			}
			if (null != userInfo.getCountry()) {
				userInfoObj.setCountry(userInfo.getCountry());
			}
			if (null != userInfo.getProfilePicFileId()) {
				userInfoObj.setProfilePicFileId(userInfo.getProfilePicFileId());
			}
			if (null != userInfo.getProfilePicFileExt()) {
				userInfoObj.setProfilePicFileExt(userInfo.getProfilePicFileExt());
			}
			if (null != userInfo.getSalutation()) {
				userInfoObj.setSalutation(userInfo.getSalutation());
			}
			if (null != userInfo.getSecondaryEmailAddress()) {
				userInfoObj.setSecondaryEmailAddress(userInfo.getSecondaryEmailAddress());
			}
			if (null != userInfo.getState()) {
				userInfoObj.setState(userInfo.getState());
			}
			if (null != userInfo.getTimeZone()) {
				userInfoObj.setTimeZone(userInfo.getTimeZone());
			}
			if (null != userInfo.getZipcode()) {
				userInfoObj.setZipcode(userInfo.getZipcode());
			}
			if (null != userInfo.getLanguage()) {
				userInfoObj.setLanguage(userInfo.getLanguage());
			}
			if (null != userInfo.getLocation()) {
				userInfoObj.setLocation(userInfo.getLocation());
			}
			if (null != userInfo.getUserDescription()) {
				userInfoObj.setUserDescription(userInfo.getUserDescription());
			}
			if (null != userInfo.getWebSite()) {
				userInfoObj.setWebSite(userInfo.getWebSite());
			}
		}
	}

}
