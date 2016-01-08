package com.myplace.common.util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.common.constant.FeedBackWebConstant;
import com.myplace.common.constant.MyPlaceBusinessConstant;
import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.constant.ClientParamConstant;
import com.myplace.common.constant.UserParameters;
import com.myplace.common.enumeration.DateTimeFormatEnum;
import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dto.AdvertisementDTO;
import com.myplace.dto.AdvertisementInfo;
import com.myplace.dto.AdvtBusinessInfo;
import com.myplace.dto.BusinessFileInfo;
import com.myplace.dto.BusinessInfo;
import com.myplace.dto.BusinessSearchVO;
import com.myplace.dto.FeedBackInfo;
import com.myplace.dto.FeedBackReplyInfo;
import com.myplace.dto.FileInfo;
import com.myplace.dto.PaymentInfo;
import com.myplace.dto.RegistrationInfo;
import com.myplace.dto.UserInfo;



public class RequestProcessorUtil {
	
	private static Logger logger = LoggerFactory.getLogger(RequestProcessorUtil.class);

	public static AdvertisementDTO enrichAdvertisementDTO(HashMap<String, Object> requestMap,AdvertisementDTO advertisementDTO ){
		if (null != requestMap) {
			AdvertisementInfo advertisementInfo =new AdvertisementInfo();
			AdvtBusinessInfo  businessInfo = new AdvtBusinessInfo();
			PaymentInfo paymentInfo = new PaymentInfo();
			if(null!=requestMap.get(MyPlaceConstant.FILE_DATA)){
				advertisementDTO.setFileInfo((FileInfo) requestMap.get(MyPlaceConstant.FILE_DATA));
				logger.debug(advertisementDTO.getFileInfo().getFile_name())	;
			}
			
			if(null!=requestMap.get(MyPlaceConstant.AD_NAME)){
				advertisementInfo.setAdvtName((String) requestMap.get(MyPlaceConstant.AD_NAME));
			}
			
			if(null!=requestMap.get(MyPlaceConstant.USERID)){
				advertisementInfo.setCreatorId(Long.parseLong(requestMap.get(MyPlaceConstant.USERID).toString()));
			}
			if(null!=requestMap.get(MyPlaceConstant.AD_HEADER)){
				advertisementInfo.setAdHeader((String) requestMap.get(MyPlaceConstant.AD_HEADER));
			}
			if(null!=requestMap.get(MyPlaceConstant.ADVT_DESC)){
				advertisementInfo.setAdvtDesc((String) requestMap.get(MyPlaceConstant.ADVT_DESC));
			}
			if(null!=requestMap.get(MyPlaceConstant.ADTYPE)){
				advertisementInfo.setAdtype((String) requestMap.get(MyPlaceConstant.ADTYPE));
			}
			if(null!=requestMap.get(MyPlaceConstant.CITY)){
				advertisementInfo.setCity((String) requestMap.get(MyPlaceConstant.CITY));
			}
			if(null!=requestMap.get(MyPlaceConstant.STATE)){
				advertisementInfo.setState((String) requestMap.get(MyPlaceConstant.STATE));
			}
			if(null!=requestMap.get(MyPlaceConstant.ZIPCODE)){
				advertisementInfo.setZipcode((String) requestMap.get(MyPlaceConstant.ZIPCODE));
			}
			if(null!=requestMap.get(MyPlaceConstant.COUNTRY)){
				advertisementInfo.setCountry((String) requestMap.get(MyPlaceConstant.COUNTRY));
			}
			if(null != requestMap.get(MyPlaceConstant.LATITUDE)){
				advertisementInfo.setLatitude(Float.parseFloat(requestMap.get(MyPlaceConstant.LATITUDE).toString()));
			}
			if(null != requestMap.get(MyPlaceConstant.LONGITUDE)){
				advertisementInfo.setLongitude(Float.parseFloat(requestMap.get(MyPlaceConstant.LONGITUDE).toString()));
			}
			if(null!=requestMap.get(MyPlaceConstant.WEB_URL)){
				advertisementInfo.setWebUrl((String) requestMap.get(MyPlaceConstant.WEB_URL));
			}
			if(null!=requestMap.get(MyPlaceConstant.ADDRESS)){
				advertisementInfo.setAddress((String) requestMap.get(MyPlaceConstant.ADDRESS));
			}
			if(null!=requestMap.get(MyPlaceConstant.PHONE1)){
				advertisementInfo.setPhoneNumber((String) requestMap.get(MyPlaceConstant.PHONE1));
			}
			if(null!=requestMap.get(MyPlaceConstant.CONTACT_PHONE)){
				advertisementInfo.setContactPhone((String) requestMap.get(MyPlaceConstant.CONTACT_PHONE));
			}
			if(null!=requestMap.get(MyPlaceConstant.CONTACT_NAME)){
				advertisementInfo.setContactName((String) requestMap.get(MyPlaceConstant.CONTACT_NAME));
			}
			if(null!=requestMap.get(MyPlaceConstant.CONTACT_EMAIL)){
				advertisementInfo.setContactEmail((String) requestMap.get(MyPlaceConstant.CONTACT_EMAIL));
			}
			if(null!=requestMap.get(MyPlaceConstant.DAILY_BUDGET)){
				advertisementInfo.setDailyBudget(Float.parseFloat( requestMap.get(MyPlaceConstant.DAILY_BUDGET).toString()));
			}
			if(null!=requestMap.get(MyPlaceConstant.STARTDATE)){
				advertisementInfo.setStartDate((String) requestMap.get(MyPlaceConstant.STARTDATE));
			}
			if(null!=requestMap.get(MyPlaceConstant.ENDDATE)){
				advertisementInfo.setEndDate((String) requestMap.get(MyPlaceConstant.ENDDATE));
			}
			if(null!=requestMap.get(MyPlaceConstant.STARTTIME)){
				advertisementInfo.setStartTime((String) requestMap.get(MyPlaceConstant.STARTTIME));
			}
			if(null!=requestMap.get(MyPlaceConstant.BUDGET)){
				advertisementInfo.setBudget(Float.parseFloat(requestMap.get(MyPlaceConstant.BUDGET).toString()));
			}
			
			if(null!=requestMap.get(MyPlaceConstant.BUSINESS_ADDRESS)){
				businessInfo.setB_address((String) requestMap.get(MyPlaceConstant.BUSINESS_ADDRESS));
			}
			
			if(null!=requestMap.get(MyPlaceConstant.CARD_NUMBER)){
				paymentInfo.setCard_number((String) requestMap.get(MyPlaceConstant.CARD_NUMBER));
			}
			if(null!=requestMap.get(MyPlaceConstant.NAME_ON_CARD)){
				paymentInfo.setName_on_card((String) requestMap.get(MyPlaceConstant.NAME_ON_CARD));
			}
			if(null!=requestMap.get(MyPlaceConstant.TRANSACTION_ID)){
				paymentInfo.setTransaction_id((String) requestMap.get(MyPlaceConstant.TRANSACTION_ID));
			}
			
			advertisementDTO.setBusinessInfo(businessInfo);
			advertisementDTO.setAdvertisementInfo(advertisementInfo);
			advertisementDTO.setPaymentInfo(paymentInfo);
			
			logger.debug("getAdvt_name == "+advertisementDTO.getAdvertisementInfo().getAdvtName())	;
			
			logger.debug("getB_address=="+advertisementDTO.getBusinessInfo().getB_address())	;
			
			logger.debug("getCard_number=="+advertisementDTO.getPaymentInfo().getCard_number())	;
			
		}
		return  advertisementDTO;
	}

	
	public static void enrichRegistrationInfo(HashMap<String, Object> requestMap, RegistrationInfo registrationVO,Map<String, Object> clientParamMap) {
		if (null != requestMap) {
			if (null != requestMap.get(UserParameters.PASSWORD)) {
				registrationVO.setPassword(requestMap.get(UserParameters.PASSWORD).toString());
			}
			if (null != requestMap.get(UserParameters.REGISTRATION_MODE)) {
				registrationVO.setRegistrationMode(Integer.parseInt(requestMap.get(UserParameters.REGISTRATION_MODE).toString()));
			}
			if (null != requestMap.get(UserParameters.PARTNER_USER_KEY)) {
				registrationVO.setUserKey(requestMap.get(UserParameters.PARTNER_USER_KEY).toString());
			}
			if (null != requestMap.get(UserParameters.PARTNER_APP_ID)) {
				registrationVO.setAppKey(requestMap.get(UserParameters.PARTNER_APP_ID).toString());
			}
			if (null != requestMap.get(UserParameters.ADVTCODE)) {
				registrationVO.setAdvtCode(requestMap.get(UserParameters.ADVTCODE).toString());
			}
			if(null != clientParamMap.get(ClientParamConstant.CLIENT_VERSION)){
				registrationVO.setCurrentClientVersion((String) clientParamMap.get(ClientParamConstant.CLIENT_VERSION));
			}
			if(null != clientParamMap.get(ClientParamConstant.PLATFORM)){
				registrationVO.setCurrentPlatform((String) clientParamMap.get(ClientParamConstant.PLATFORM));
			}
			if(null != clientParamMap.get(ClientParamConstant.LATITUDE)){
				registrationVO.setLatitude(Float.parseFloat(clientParamMap.get(ClientParamConstant.LATITUDE).toString()));
			}
			if(null != clientParamMap.get(ClientParamConstant.LONGITUDE)){
				registrationVO.setLongitude(Float.parseFloat(clientParamMap.get(ClientParamConstant.LONGITUDE).toString()));
			}
			if(null !=  clientParamMap.get(ClientParamConstant.ADDREASS)){
				registrationVO.setLastLocation((String) clientParamMap.get(ClientParamConstant.ADDREASS));
			}
			
			
			enrichUserVO(requestMap, registrationVO,clientParamMap);
		}
	}

	public static void enrichUserVO(HashMap<String, Object> requestMap, UserInfo userVO,Map<String, Object> clientParamMap) {
		if (null != requestMap) {
			if (null != requestMap.get(UserParameters.CITY)) {
				userVO.setCity((String)requestMap.get(UserParameters.CITY));
			}else if (null!=clientParamMap && null != clientParamMap.get(UserParameters.CITY)) {
				userVO.setCity((String)clientParamMap.get(UserParameters.CITY));
			}
			if (null != requestMap.get(UserParameters.CONTACT_ADDRESS_LINE1)) {
				userVO.setContactAddressLine1(requestMap.get(UserParameters.CONTACT_ADDRESS_LINE1).toString());
			}
			if (null != requestMap.get(UserParameters.CONTACT_ADDRESS_LINE2)) {
				userVO.setContactAddressLine2(requestMap.get(UserParameters.CONTACT_ADDRESS_LINE2).toString());
			}
			if (null != requestMap.get(UserParameters.CONTACT_NUMBER)) {
				userVO.setContactNumber(requestMap.get(UserParameters.CONTACT_NUMBER).toString());
			}
			
			if (null != requestMap.get(UserParameters.COUNTRY)) {
				userVO.setCountry((String)requestMap.get(UserParameters.COUNTRY));
			}else if (null!=clientParamMap && null != clientParamMap.get(UserParameters.COUNTRY)) {
				userVO.setCountry((String)clientParamMap.get(UserParameters.COUNTRY));
			}
			//logger.debug("enrichUserVO--"+clientParamMap.get(UserParameters.COUNTRY));
			if (null != requestMap.get(UserParameters.DOB)) {
				logger.debug("enrichUserVO ::DOB :: "+ requestMap.get(UserParameters.DOB));
				String dob = requestMap.get(UserParameters.DOB).toString();
				userVO.setDob(DateTimeUtils.getDateObject(dob, DateTimeFormatEnum.WEB_DATE_FORMAT));
			}
			if (null != requestMap.get(UserParameters.CONTACT_NAME)) {
				userVO.setContactName(requestMap.get(UserParameters.CONTACT_NAME).toString());
			}
			if (null != requestMap.get(UserParameters.FIRST_NAME)) {
				userVO.setFirstName(requestMap.get(UserParameters.FIRST_NAME).toString());
			}
			
			if (null != requestMap.get(UserParameters.LAST_NAME)) {
				userVO.setLastName(requestMap.get(UserParameters.LAST_NAME).toString());
			}
			if (null != requestMap.get(UserParameters.GENDER)) {
				userVO.setGender(Integer.parseInt(requestMap.get(UserParameters.GENDER).toString()));
			}
			if (null != requestMap.get(UserParameters.BUSINESS_NAME)) {
				userVO.setBusinessName(requestMap.get(UserParameters.BUSINESS_NAME).toString());
			}
			if (null != requestMap.get(UserParameters.PROFILE_PIC_FILE_ID)) {
				userVO.setProfilePicFileId(requestMap.get(UserParameters.PROFILE_PIC_FILE_ID).toString());
			}
			if (null != requestMap.get(UserParameters.SATUTATION)) {
				userVO.setSalutation(requestMap.get(UserParameters.SATUTATION).toString());
			}
			if (null != requestMap.get(UserParameters.SECONDARY_EMAIL_ADDRESS)) {
				userVO.setSecondaryEmailAddress(requestMap.get(UserParameters.SECONDARY_EMAIL_ADDRESS).toString());
			}
			if (null != requestMap.get(UserParameters.STATE)) {
				userVO.setState((String)requestMap.get(UserParameters.STATE));
			}else if (null!=clientParamMap && null != clientParamMap.get(UserParameters.STATE)) {
				userVO.setState((String)clientParamMap.get(UserParameters.STATE));
			}
			if (null != requestMap.get(UserParameters.TIME_ZONE)) {
				userVO.setTimeZone(requestMap.get(UserParameters.TIME_ZONE).toString());
			}
			if (null != requestMap.get(UserParameters.ZIPCODE)) {
				userVO.setZipcode(requestMap.get(UserParameters.ZIPCODE).toString());
			}
			if (null != requestMap.get(UserParameters.USER_NAME)) {
				userVO.setUserName((requestMap.get(UserParameters.USER_NAME).toString()).toLowerCase());
			}
			if (null != requestMap.get(UserParameters.USER_ID)) {
				userVO.setId(Long.parseLong(requestMap.get(UserParameters.USER_ID).toString()));
			}
			if (null != requestMap.get(UserParameters.USER_DESC)) {
				userVO.setUserDescription(requestMap.get(UserParameters.USER_DESC).toString());
			}
			if (null != requestMap.get(UserParameters.WEB_SITE)) {
				userVO.setWebSite(requestMap.get(UserParameters.WEB_SITE).toString());
			}
			if (null != requestMap.get(UserParameters.USER_LANGUAGE)) {
				userVO.setLanguage(requestMap.get(UserParameters.USER_LANGUAGE).toString());
			}else if (null!=clientParamMap && null != clientParamMap.get(ClientParamConstant.LANGUAGE)) {
				userVO.setLanguage((String)clientParamMap.get(ClientParamConstant.LANGUAGE));
			}
			if (null != requestMap.get(ClientParamConstant.LOCATION)) {
				userVO.setLocation(requestMap.get(ClientParamConstant.LOCATION).toString());
			}else if(null!=clientParamMap && null != clientParamMap.get(ClientParamConstant.LOCATION)){
				userVO.setLocation((String) clientParamMap.get(ClientParamConstant.LOCATION));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static BusinessInfo enrichBusinessInfo(HashMap<String, Object> requestMap,BusinessInfo businessInfo ){
		if (null != requestMap) {
			
			if(null!=requestMap.get(MyPlaceConstant.B_FILE_DATA)){
				businessInfo.setBusinessFileInfo((List<BusinessFileInfo>) requestMap.get(MyPlaceConstant.B_FILE_DATA));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.USERID)){
				businessInfo.setUserId(Long.parseLong(requestMap.get(MyPlaceBusinessConstant.USERID).toString()));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BID)){
				businessInfo.setBussId(Long.parseLong(requestMap.get(MyPlaceBusinessConstant.BID).toString()));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BNAME)){
				businessInfo.setBussName((String) requestMap.get(MyPlaceBusinessConstant.BNAME));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BADDRESS)){
				businessInfo.setBussAddress((String) requestMap.get(MyPlaceBusinessConstant.BADDRESS));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BCITY)){
				businessInfo.setBussCity((String) requestMap.get(MyPlaceBusinessConstant.BCITY));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BSTATE)){
				businessInfo.setBussState((String) requestMap.get(MyPlaceBusinessConstant.BSTATE));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BCOUNTRY)){
				businessInfo.setBussCountry((String) requestMap.get(MyPlaceBusinessConstant.BCOUNTRY));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BZIP)){
				businessInfo.setBussZip((String) requestMap.get(MyPlaceBusinessConstant.BZIP));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BPHONE)){
				businessInfo.setBussPhone((String) requestMap.get(MyPlaceBusinessConstant.BPHONE));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BEMAIL)){
				businessInfo.setBussEmail((String) requestMap.get(MyPlaceBusinessConstant.BEMAIL));
			}
			if(null != requestMap.get(MyPlaceBusinessConstant.BLATITUDE)){
				businessInfo.setBussLat(Float.parseFloat(requestMap.get(MyPlaceBusinessConstant.BLATITUDE).toString()));
			}
			if(null != requestMap.get(MyPlaceBusinessConstant.BLONGITUDE)){
				businessInfo.setBussLong(Float.parseFloat(requestMap.get(MyPlaceBusinessConstant.BLONGITUDE).toString()));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BWEB)){
				businessInfo.setBussWeb((String) requestMap.get(MyPlaceBusinessConstant.BWEB));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BDESC)){
				businessInfo.setBussDesc((String) requestMap.get(MyPlaceBusinessConstant.BDESC));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BSERVICE)){
				businessInfo.setBussService((String) requestMap.get(MyPlaceBusinessConstant.BSERVICE));
			}
			if(null!=requestMap.get(MyPlaceBusinessConstant.BSPECIALITY)){
				businessInfo.setBussSpeciality((String) requestMap.get(MyPlaceBusinessConstant.BSPECIALITY));
			}
			if(null != requestMap.get(MyPlaceBusinessConstant.CATID)){
				businessInfo.setCatId(Long.parseLong(requestMap.get(MyPlaceBusinessConstant.CATID).toString()));
			}
			if(null != requestMap.get(MyPlaceBusinessConstant.SUBCATID)){
				businessInfo.setSubCatId((String)requestMap.get(MyPlaceBusinessConstant.SUBCATID).toString());
			}
			if(null != requestMap.get(MyPlaceBusinessConstant.BSTARTYEAR)){
				String startDate= (String) requestMap.get(MyPlaceBusinessConstant.BSTARTYEAR);
				businessInfo.setBussStartDate(startDate);	
			}else{
				businessInfo.setBussStartDate("");
			}
			logger.debug("businessInfo=="+businessInfo)	;
			
		}
		return  businessInfo;
	}
	
	public static BusinessSearchVO enrichSearchVO(HashMap<String,Object> requestMap) {
		BusinessSearchVO searchVO = new BusinessSearchVO();
		if(null != requestMap.get(MyPlaceBusinessConstant.BLATITUDE)){
			searchVO.setLatitude(Float.parseFloat(requestMap.get(MyPlaceBusinessConstant.BLATITUDE).toString()));
		}
		if(null != requestMap.get(MyPlaceBusinessConstant.BLONGITUDE)){
			searchVO.setLongitude(Float.parseFloat(requestMap.get(MyPlaceBusinessConstant.BLONGITUDE).toString()));
		}
		if (null != requestMap.get(MyPlaceBusinessConstant.BZIP)) {
			searchVO.setZipCode((String) requestMap.get(MyPlaceBusinessConstant.BZIP));
		}
		if (null != requestMap.get(MyPlaceBusinessConstant.TEXT)) {
			searchVO.setText((String) requestMap.get(MyPlaceBusinessConstant.TEXT));
		}
		if (null != requestMap.get(MyPlaceBusinessConstant.TYPE)) {
			searchVO.setType((String) requestMap.get(MyPlaceBusinessConstant.TYPE));
		}
		if (null != requestMap.get(MyPlaceBusinessConstant.SUBCATID)) {
			searchVO.setSubCatId((String) requestMap.get(MyPlaceBusinessConstant.SUBCATID));
		}
		if (null != requestMap.get(MyPlaceBusinessConstant.CATID)) {
			searchVO.setCatId(Integer.parseInt(requestMap.get(MyPlaceBusinessConstant.CATID).toString()));
		}
		if (null != requestMap.get(MyPlaceBusinessConstant.USERID)) {
			searchVO.setUserId(Long.parseLong(requestMap.get(MyPlaceBusinessConstant.USERID).toString()));
		}
		if (null != requestMap.get(MyPlaceBusinessConstant.DISTANCE)) {
			searchVO.setDistance(Integer.parseInt(requestMap.get(MyPlaceBusinessConstant.DISTANCE).toString()));
		}else{
			searchVO.setDistance(MyPlaceBusinessConstant.DEFAULT_DISTANCE);
		}
		/*else if(null != userId){
			searchVO.setUserId(userId);
		}*/
		return searchVO;
	}
	
	
	public static FeedBackInfo enrichFeedBackInfoInfo(HashMap<String, Object> requestMap,FeedBackInfo feedBackInfo ){
		if (null != requestMap) {
			
			if(null!=requestMap.get(FeedBackWebConstant.USERID)){
				feedBackInfo.setUserId(Long.parseLong(requestMap.get(FeedBackWebConstant.USERID).toString()));
			}
		
			if(null!=requestMap.get(FeedBackWebConstant.EMAIL)){
				feedBackInfo.setEmail((String) requestMap.get(FeedBackWebConstant.EMAIL));
			}
			if(null!=requestMap.get(FeedBackWebConstant.FEED_BACK_TEXT)){
				feedBackInfo.setFeedBackText((String) requestMap.get(FeedBackWebConstant.FEED_BACK_TEXT));
			}
			
			if(null!=requestMap.get(FeedBackWebConstant.FEED_BACK_ID)){
				feedBackInfo.setFeedBackId(Long.parseLong(requestMap.get(FeedBackWebConstant.FEED_BACK_ID).toString()));
			}
			if(null!=requestMap.get(FeedBackWebConstant.STATUS)){
				feedBackInfo.setStatus(Byte.parseByte(requestMap.get(FeedBackWebConstant.STATUS).toString()));
			}	
		}	
		return feedBackInfo;
	}
	
	public static FeedBackReplyInfo enrichFeedBackReplyInfo(HashMap<String, Object> requestMap,FeedBackReplyInfo feedBackReplyInfo ){
		if (null != requestMap) {
			
			if(null!=requestMap.get(FeedBackWebConstant.REPLYING_USER_ID)){
				feedBackReplyInfo.setReplyingUserId(Long.parseLong(requestMap.get(FeedBackWebConstant.REPLYING_USER_ID).toString()));
			}
			if(null!=requestMap.get(FeedBackWebConstant.REPLY_TEXT)){
				feedBackReplyInfo.setReplyText((String) requestMap.get(FeedBackWebConstant.REPLY_TEXT));
			}
			if(null!=requestMap.get(FeedBackWebConstant.FEED_BACK_ID)){
				feedBackReplyInfo.setFeedBackId(Long.parseLong(requestMap.get(FeedBackWebConstant.FEED_BACK_ID).toString()));
			}	
			if(null!=requestMap.get(FeedBackWebConstant.FEED_BACK_REPLY_ID)){
				feedBackReplyInfo.setReplyId(Long.parseLong(requestMap.get(FeedBackWebConstant.FEED_BACK_REPLY_ID).toString()));
			}	
			if(null!=requestMap.get(FeedBackWebConstant.STATUS)){
				feedBackReplyInfo.setStatus(Byte.parseByte(requestMap.get(FeedBackWebConstant.STATUS).toString()));
			}	
		}	
		return feedBackReplyInfo;
	}
	
	public static void enrichUserPushInfo(HashMap<String, Object> requestMap,UserPushInfo userPushInfo,Map<String, Object> clientParamMap) {
		if (null != requestMap) {
			if (null != requestMap.get(UserParameters.USERID)) {
				userPushInfo.setUserId(Long.parseLong(requestMap.get(UserParameters.USERID).toString()));
			}
		
			if (null != requestMap.get(UserParameters.DEVICE_ID)) {
				userPushInfo.setDeviceId(requestMap.get(UserParameters.DEVICE_ID).toString());
			}
			if (null != requestMap.get(UserParameters.PUSH_KEY)) {
				userPushInfo.setPushKey(requestMap.get(UserParameters.PUSH_KEY).toString());
			}
			
			if(null != clientParamMap.get(ClientParamConstant.PLATFORM)){
				if("ANDROID".equalsIgnoreCase(clientParamMap.get(ClientParamConstant.PLATFORM).toString())){
					userPushInfo.setPlatform((byte)1);
				}else if("IPHONE".equalsIgnoreCase(clientParamMap.get(ClientParamConstant.PLATFORM).toString())){
					userPushInfo.setPlatform((byte)2);
				}
			}else{
				//by default set platform as android
				userPushInfo.setPlatform((byte)1);
			}
		}
	}
}
