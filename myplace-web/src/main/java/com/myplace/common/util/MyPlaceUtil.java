package com.myplace.common.util;

import org.apache.commons.lang.RandomStringUtils;

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
		String appUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.SERVER_URL)+"/"+ myplaceProperties.getProperty(MyPlacePropertyKeys.APP_NAME);
		return appUrl;
	 
	}
 
 
	
}
