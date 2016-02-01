package com.myplace.rest.constant;



public interface MyPlaceWebConstant {
	String STATUS = "status";
	String MESSAGE = "message";
	String CODE = "code";
	String STATUS_SUCCESS = "success";
	String STATUS_ERROR = "error";
	String STATUS_WARNING = "warning";
	String MEDIA_CREATE_ERROR = "Media creation is failed due to some reason.";
	String ADVT_CREATE_ERROR = "Advt creation is failed due to some reason.";
	String DATA_NOT_UPLOADED = "Data upload parameters are null.";
	String ERROR_MESSAGE = "Request is failed due to some reason.";
	String NO_RESULT_FOUND = "No result found.";
	String DEFAULT_VIEW_NAME = "json";
	String RESPONSE = "jsonData";
	String JSP_RESPONSE = "respObj";
	String JSP_CATEGORY = "catObj";
	String JSP_SEARCH_RESPONSE = "searchRespObj";
	String JSP_SEARCH_CRITERION = "searchObj";
	String REQUEST_TYPE = "requesttype";
	Integer ERROR_CODE = 0;
	Integer SUCCESS_CODE = 1;
	Integer DEFAULT_ADVT_LIMIT = 10;
	String YES = "1";
	String CHALK_AUTHETICATION_PROPERTY = "authparams";
	String CHALK_AUTHETICATION_IMEI_PARAM = "imei";
	String CHALK_AUTHETICATION_USER_NAME_PARAM = "username";
	String CHALK_AUTHETICATION_AUTHKEY_PARAM = "authkey";
	String CHALK_AUTHETICATION_PASSWORD_PARAM = "password";
	String CHALK_AUTHETICATION_LOGIN_MODE_PARAM = "loginmode";
	String CHALK_AUTHETICATION_PARTNER_USER_KEY_PARAM = "partneruserkey";
	String CHALK_AUTHETICATION_APP_ID_PARAM = "appid";
	String CHALK_AUTHETICATION_AUTHKEY = "8be7a063a19fd760850351fcbc43eec9";
	String LOCATION="loc";
	String TAG="tag";
	String ADVT = "advt";
	String LONG = "long";
	String LAT = "lat";
	String ZOOM = "zoom";
	String ADVT_TEMPLATE = "advtTemplate";
	String USERID = "userId";
	String ADVTCODE = "advtCode";
	String TYPE = "type";
	String COUNTRY_CODE = "countryCode";
	String DEFAULT_COUNTRY_CODE = "IN";
	String CATEGORIES = "cats";
	String ABOUT_US = "abt";
	String FEEDBACK = "feedback";
	String SUB_CATEGORIES = "subCats";
	String CATEGORY_ID = "catId";
	String BUSINESS_ID = "businessId";
	String BUSINESS_LIST = "bussList";
	String BUSINESS_DETAIL = "bussDtl";
	String USER_DETAIL = "usrDtl";
	String SLIMIT="sLimit";
	String NOTIFICATION_LIST = "notifList";
	String EMAIL_VERIFICATION_FAILURE_VIEW_NAME = "emailVerificationFailure";
	String EMAIL_VERIFICATION_SUCCESS_VIEW_NAME = "emailVerificationSuccess";
	String PASSWORD_CHANGE_FAILURE_VIEW_NAME = "passwordChangeFailure";
	String PASSWORD_CHANGE_SUCCESS_VIEW_NAME = "passwordChangeSuccess";
	String REASONLIST = "reasonList";
	String BUSINESS_REPORT_LIST = "bReportList";
	String APP_TYPE= "appType";
	
	//name of jsp pages for output
	String USER_PROFILE = "userProfile";
	String EDIT_USER_PROFILE = "editUserProfile";
	String BUSINESS_PROFILE = "bussProfile";
	String BUSINESS_LIST_PROFILE = "bussListProfile";
	String EDIT_BUSINESS_PROFILE = "editBussProfile";
	String SEARCH= "search";
	String SEARCH_RESPONSE = "searchResult";
	String LOGIN = "login";
	String REGISTER = "register";
	String FORGET_PASSWORD = "forgetPassword"; //UI to send request for  forget password
	String VERIFY_ACCOUNT = "verifyAccount";
	String CHANGE_PASSWORD = "changePassword";
	String RESET_PASSWORD = "resetPassword";
	String ABOUT_US_PAGE = "aboutUs";
	
	//API URL
	String GET_USER_PVT_PROFILE_API = "userProfile";
	String GET_USER_PUB_PROFILE_API = "businessProfile";
	String EDIT_USER_PROFILE_API =   "usr/pvt/editprofile";//will return UI
	String UPDATE_USER_PROFILE_API =   "usr/pvt/updateprofile";//will update user profile
	String GET_BUSINESS_PROFILE_API = "business/pub/buss/";
	String GET_MY_BUSINESS_LIST_API = "business/pvt/my/";
	String GET_MY_BUSINESS_PROFILE_API = "business//pvt/my/";
	String EDIT_BUSINESS_PROFILE_API = "business/pvt/editbuss"; //will return UI
	String UPDATE_BUSINESS_PROFILE_API = "business/pvt/updatebuss";//will update business profile
	String DELETE_BUSINESS_PROFILE_API = "business/pvt/delbuss";
	String CHANGE_PROFILE_PASSWORD_API = "login";
	String SEARCH_BUSINESS_API = "login";
	String VERIFY_USER_ACCOUNT_API ="usrauth/pvt/verifyaccount";//will return Verify UI
	String CHANGE_USER_PASSWORD_API ="usr/pvt/loadchangepassword";//will return change pasword UI
	
	
}
