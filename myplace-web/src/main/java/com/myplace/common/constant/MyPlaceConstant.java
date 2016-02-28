package com.myplace.common.constant;

public interface MyPlaceConstant {
	Byte MEDIA_ACTIVE_STATUS = 1;
	String MYPLACE_REQ_PARAMS_MAP = "myplaceRequestParamMap";
	String MYPLACE_CLIENT_PARAM_SEPERATOR = "::";
	String MYPLACE_CLIENT_KEY_SEPERATOR = "##";
	String USER_NAME="username";
	String PROFILE_URL="profileUrl";
	String USERID = "userId";
	String ADVTCODE = "advtCode";
	//ADVT DETAILS
	String ADTYPE = "adtype";
	String OPERATION = "operation";
	String TYPE = "type";
	String FILE_DATA = "fileInfo";
	String AD_NAME = "ad_name";
	String AD_HEADER = "ad_header";
	String PHONE1 = "phone1";
	String CONTACT_PHONE = "contact_phone";
	String ADVT_DESC = "advt_desc";
	String CONTACT_NAME="c_name";
	String CONTACT_EMAIL="c_email";
	String ADDRESS ="address";
	String CITY = "city";
	String ZIPCODE = "zipcode";
	String STATE = "state";
	String COUNTRY = "country";
	String LATITUDE = "lat";
	String LONGITUDE = "long";
	String BUDGET = "budget";
	String DAILY_BUDGET ="daily_budget";
	String DISTANCE = "distance";
	String STARTDATE = "startDate";
	String ENDDATE = "endDate";
	String WEB_URL = "web_url";
	String STARTTIME = "startTime";
	String STATUS = "status";
	
	//Advt BUSINESS DETAILS
	String BUSINESS_ADDRESS = "b_address";
	String INFORMATION = "information";
    String BUSINESS_WEBSITE="b_website";
    String BUSINESS_SERVICE_DESC="b_service_desc";
    String BUSINESS_SPECIALITIES="b_specialities";
    
	//TRANSACTION DETAILS
	String CARD_NUMBER = "card_number";
	String NAME_ON_CARD = "name_on_card";
	String TRANSACTION_ID = "transaction_id";
	
	//default
	String DEFAULT_COUNTRY="United States";
	String DEFAULT_CITY="US";
	String DEFAULT_STATE="US";
	String DEFAULT_LANGUAGE="English";
	String DEFAULT_MALE="male";
	String DEFAULT_FEMALE="female";
	String ADVT_FEED = "advtList";
	String ADVT_STATS = "advtStats";
	
	//BUSINESS DETAILS
	String B_ADDRESS = "b_address";
	String B_INFORMATION = "information";
    String B_WEBSITE="b_website";
    String B_SERVICE_DESC="b_service_desc";
    String B_SPECIALITIES="b_specialities";
    String B_FILE_DATA = "fileInfo";
	
    //PUSH CONSTANT
    
    String PUSH_MESSAGE = "pushMessage";
    String DEVICE_KEY = "deviceKey";
    
    //default file info
    String DEFAULT_FILE_DATA = "defaultfileInfo";
    String ID = "id";
    String MEDIA_TYPE = "type";
    int UNKNOWN_TYPE=0;
	int MALE_TYPE=1;
	int FEMALE_TYPE=2;
	int CAT_TYPE=3;
	int SUBCAT_TYPE=4;
	int DEFAULT_CAT_ID_FOR_IMAGE=0;
	
	//status for email verification
	int NOT_VERIFIED_STATUS=0;
	int VERIFIED_STATUS=1;
	int EXPIRED_STATUS=2;
	//status for user
	int INACTIVE_STATUS=0;
	int ACTIVE_STATUS=1;
	int BLOCKED_STATUS=2;
	
	//status for forget password
	int UNUSED_STATUS=0;
	int USED_STATUS=1;
	int CODE_EXPIRED_STATUS=2;
	
	//status for business status
	 byte BUSS_ACTIVE_STATUS=1;
	 byte BUSS_INACTIVE_STATUS=2;
	 byte BUSS_DELETED_STATUS=3;
	 
	//type for reason list 
	 byte BUSS_TYPE=1;
	 byte USER_TYPE=2;
	 
	 
	
	
	
}
