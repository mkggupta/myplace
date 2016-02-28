package com.myplace.common.util;

import org.apache.commons.lang.RandomStringUtils;

import com.myplace.rest.constant.MyPlaceWebConstant;

public class MyPlaceUtil {

	public static String convertStringToHex(String str){
		 
		  char[] chars = str.toCharArray();
		  StringBuffer hex = new StringBuffer();
		  for(int i = 0; i < chars.length; i++){
		    hex.append(Integer.toHexString((int)chars[i]));
		  }
		  return hex.toString();
		}
	
	public static String generateRandomAlphanumericKey(int length) {
		return RandomStringUtils.random(length, true, true);
	}
	
	public static String getServerBaseUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String baseUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL);
	 return baseUrl;
	 
	}
 
	
	public static String getServerUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String serverUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.SERVER_URL);
	 return serverUrl;
	 
	}
	
	public static String getApplicationUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String appUrl = new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.SERVER_URL)).append("/").append(myplaceProperties.getProperty(MyPlacePropertyKeys.APP_NAME)).toString();
		return appUrl;
	 
	}
 
	public static String getMyBusinessListUrl(long userId) {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String bussListUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.GET_MY_BUSINESS_LIST_API).append(userId).toString();
	 return bussListUrl;
	 
	}
	
	public static String getBusinessDetailUrl(long businessId) {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String bussUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.GET_BUSINESS_PROFILE_API).append(businessId).toString();
	  return bussUrl;
	 
	}
	
	public static String getMyBusinessDetailUrl(long userId,long businessId) {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String bussUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.GET_MY_BUSINESS_PROFILE_API).append(userId).append("/").append(businessId).toString();
	   return bussUrl;
	 
	}
	
	public static String getEditBussProfileUIUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String proUpdateUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.EDIT_BUSINESS_PROFILE_API).toString();
	  return proUpdateUrl;
	 
	}
	
	public static String getUpdateBusinessApiUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String changePassUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.UPDATE_BUSINESS_PROFILE_API).toString();
	  return changePassUrl;
	 
	}
	
	public static String getDeleteBusinessApiUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String changePassUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.DELETE_BUSINESS_PROFILE_API).toString();
	  return changePassUrl;
	 
	}
	
	public static String getEditProfileUIUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String proUpdateUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.EDIT_USER_PROFILE_API).toString();
	  return proUpdateUrl;
	 
	}
	
	public static String getUpdateProfileApiUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String changePassUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.UPDATE_USER_PROFILE_API).toString();
	  return changePassUrl;
	 
	}
	public static String getChangePassUIUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String changePassUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.CHANGE_USER_PASSWORD_API).toString();
	  return changePassUrl;
	 
	}
	
	public static String getVerifyAccountUIUrl() {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String verifyAccUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.VERIFY_USER_ACCOUNT_API).toString();
	  return verifyAccUrl;
	 
	}
	
	public static String getReportUIUrl(Long businessId,int appType) {
	 	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
		String proReportUrl =new StringBuilder(myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL)).append(MyPlaceWebConstant.REPORT_UI_API).append(appType).append("/").append(businessId).append("/").toString();
	   return proReportUrl;
	 
	}
}
