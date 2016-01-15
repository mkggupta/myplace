package com.myplace.service.user.service.v1_0;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.user.util.UserUtils;
import com.myplace.common.util.StorageUtil;
import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.media.MediaDAO;
import com.myplace.dao.modules.user.UserDAO;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.dto.RegistrationInfo;
import com.myplace.dto.UserAuth;
import com.myplace.dto.UserFileInfo;
import com.myplace.dto.UserInfo;
import com.myplace.dto.UserThirdPartyAuth;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.user.exception.DeviceRegFailedException;
import com.myplace.service.user.exception.UserServiceFailedException;
import com.myplace.service.user.exception.UserServiceValidationFailedException;

public class UserServiceImpl implements UserService {


	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserDAO userDAO ;
	private MediaDAO mediaDAO;
	
	@Autowired
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override	
	public Long regLoginUser(RegistrationInfo registrationInfo) throws UserServiceFailedException,
	UserServiceValidationFailedException {
/*	public Long regLoginUser(RegistrationInfo registrationInfo,Map<String, Object> clientMap) throws UserServiceFailedException,
			UserServiceValidationFailedException {*/
		//UserServiceValidator.validateRegistrationRequest(registrationInfo);
		Long userId = null;
		try {
			if(registrationInfo.getRegistrationMode()==0){
				return -1l;
			}
			UserAuth userAuth = UserUtils.transformRegistrationInfoToUserAuth(registrationInfo, true);
			logger.debug("userAuth--"+userAuth);
			if(null!=userAuth){
				try {
					if(registrationInfo.getRegistrationMode()==4 || registrationInfo.getRegistrationMode()==5){
						userId = userDAO.getUserIdByNamePassword(registrationInfo.getUserName(),registrationInfo.getPassword());
					}else{
						userId = userDAO.getUserExists(registrationInfo.getUserKey(), registrationInfo.getRegistrationMode());
					}
				} catch (DataAccessFailedException e) {
					throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
				}
				logger.debug("userId--"+userId);
				//user is exist and possibly trying to login 
				if(null!=userId && userId>0){
					return userId;
				}else{
					//user is trying to register
					// again check that user is not exist in our db
					try {
						if(registrationInfo.getRegistrationMode()==4 || registrationInfo.getRegistrationMode()==5){
							userId = userDAO.getUserIdByNameForApp(registrationInfo.getUserName());
						}
					} catch (DataAccessFailedException e) {
						throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
					}
					logger.debug("userId after app check--"+userId);
					//user is exist and possibly trying to login 
					if(null!=userId && userId>0){
						return -1l;
					}else{ 
					registrationInfo.setId(userDAO.saveUserAuth(userAuth));
					logger.debug("after saving userauth user id --"+registrationInfo.getId() +" userAuth ="+userAuth.getId());
					userId = registrationInfo.getId();
					UserInfo userInfo = UserUtils.transformRegistrationInfoToUserInfo(registrationInfo);
					logger.debug("userInfo--"+userInfo);
					if(null!=userInfo){
						if(registrationInfo.getCountry()==null){
							userInfo.setCountry(MyPlaceConstant.DEFAULT_COUNTRY);
						}
						if(registrationInfo.getLanguage()==null){
							userInfo.setLanguage(MyPlaceConstant.DEFAULT_LANGUAGE);
						}
						userInfo.setPrimaryEmailAddress(registrationInfo.getUserName());
						if(StringUtils.isNotBlank(registrationInfo.getLastName())){
							userInfo.setContactName(registrationInfo.getFirstName() +" "+registrationInfo.getLastName());
						}else if(StringUtils.isNotBlank(registrationInfo.getFirstName())){
							userInfo.setContactName(registrationInfo.getFirstName());
						}else{
							userInfo.setContactName(registrationInfo.getUserName());
						}
						userDAO.saveUserInfo(userInfo);
						logger.debug("after saving userInfo  id ="+userAuth.getId());
						
						//save user profile pic data
						if(null!= userInfo.getUserFileInfo() && userInfo.getUserFileInfo().size()>0){
							try {
								List<UserFileInfo> userFileInfoList = userInfo.getUserFileInfo();
								logger.debug("afteruserFileInfoList ="+userFileInfoList);
								for(UserFileInfo userFileInfo:userFileInfoList){
									userFileInfo.setUserId(userId);
									mediaDAO.saveUserFileInfo(userFileInfo);
									logger.debug("afteru saveUserFileInfo for  userId="+userId);
								}
							} catch (Exception e) {
								//we are not throwing error as profile pic saving should not  interrupt user reg
								logger.error("problem in saving user pic for  userId="+userId +", error is "+ e.getLocalizedMessage());	
							}
						}
					}
					if (registrationInfo.getRegistrationMode()>0 && registrationInfo.getRegistrationMode()<4) {
						UserThirdPartyAuth userThirdPartyAuth = new UserThirdPartyAuth(userAuth.getId(), registrationInfo.getRegistrationMode(),
								registrationInfo.getUserKey(), registrationInfo.getAppKey());
						userDAO.saveUserThirdPartyAuth(userThirdPartyAuth);
					}
				}
				}
			}
		} catch (DataUpdateFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		return userId;
	}
	
	
	public UserInfo getUserProfile(long userId) throws UserServiceFailedException, UserServiceValidationFailedException{
		UserInfo userInfo = null;
		try {
			String userName = userDAO.getUserNameById(userId);
			if(StringUtils.isNotBlank(userName)){
				userInfo = userDAO.getUserProfile(userId);
				userInfo.setUserName(userName);
				List<String> pImgUrls = new ArrayList<String>();
				List<UserFileInfo> userFileInfoList =mediaDAO.getUserFileInfoByUserId(userId);
				if(null!=userFileInfoList && userFileInfoList.size()>0){
					for(UserFileInfo userFileInfo:userFileInfoList){
						pImgUrls.add(StorageUtil.getProfileImageUrl(userFileInfo));
					}
				}else{
					//get default pic  0-unknown,1-male,2-female gender
					List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByType(userInfo.getGender());
					if(null!=defaultFileInfoList){
						for(DefaultFileInfo defaultFileInfo:defaultFileInfoList){
							pImgUrls.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));
						}	
					}
				}
				userInfo.setpImgUrls(pImgUrls);
			}
		} catch (DataAccessFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		
		return userInfo;
	}
	
	public UserInfo updateUser(UserInfo userInfo) throws UserServiceFailedException{
		try {
			UserInfo userInfoObj = null;
			try {
				userInfoObj = userDAO.getUserProfile(userInfo.getId());
				UserUtils.updateDBUserVO(userInfoObj, userInfo);
			} catch (DataAccessFailedException e) {
				if (null == userInfoObj || userInfoObj.getId() == 0) {
					logger.debug("User not found userId: " + userInfoObj);
					throw new UserServiceFailedException(ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION);
				}
			}
			try {
				userDAO.updateUser(userInfoObj);
				//user profile pic update
				List<String> pImgUrlsList = new ArrayList<String>();
				if(null!= userInfoObj.getUserFileInfo() && userInfoObj.getUserFileInfo().size()>0){
					List<UserFileInfo> userFileInfoList = userInfoObj.getUserFileInfo();
					mediaDAO.deleteUserFileInfo(userInfo.getId());					
					for(UserFileInfo userFileInfo:userFileInfoList){
						userFileInfo.setUserId(userInfo.getId());
						mediaDAO.saveUserFileInfo(userFileInfo);
						logger.debug(" -saveUserFileInfo for  userId="+userInfo.getId());
						pImgUrlsList.add(StorageUtil.getProfileImageUrl(userFileInfo));
					}

				}else{
					List<UserFileInfo> userFileInfoList =mediaDAO.getUserFileInfoByUserId(userInfo.getId());
					if(null!=userFileInfoList && userFileInfoList.size()>0){
						for(UserFileInfo userFileInfo:userFileInfoList){
							pImgUrlsList.add(StorageUtil.getProfileImageUrl(userFileInfo));
						}
					}else{
					//get default pic  0-unknown,1-male,2-female gender
						List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByType(userInfo.getGender());
						if(null!=defaultFileInfoList){
							for(DefaultFileInfo defaultFileInfo:defaultFileInfoList){
								pImgUrlsList.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));
							}	
						}
					}
				}
				if(null!= pImgUrlsList && pImgUrlsList.size()>0){
					userInfoObj.setpImgUrls(pImgUrlsList);
					userInfoObj.setUserFileInfo(null);
				}
				
			} catch (DataUpdateFailedException e) {
				logger.error("Exception in updating user details in database for the user : " + userInfoObj + " error  : " + e.getMessage());
				throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
			}
			return  userInfoObj;
		} catch (Exception e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public Long regPushDevice(UserPushInfo userPushInfo) throws DeviceRegFailedException, UserServiceFailedException{
		Long userId = null;
		 try {
			if(userDAO.isUserExists(userPushInfo.getUserId())){
				userPushInfo.setStatus(true);
				userDAO.saveUpdateUserPushInfo(userPushInfo);
				userId= userPushInfo.getUserId();
			 }else{
				 userId=-1l;
			 }
		} catch (DataAccessFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		} catch (DataUpdateFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		
		return userId;
	}
	
	public void updateUserPushStatus(long userId, String pushStatus) throws UserServiceFailedException{
		try {
			userDAO.updateUserPushStatus(userId, pushStatus);
		} catch (DataUpdateFailedException e) {
			logger.error("Exception in updateUserPushStatus"+e.getLocalizedMessage(),e);
			throw new UserServiceFailedException(ErrorCodesEnum.USER_PUSH_UPDATE_EXCEPTION);
		}
	}

}
