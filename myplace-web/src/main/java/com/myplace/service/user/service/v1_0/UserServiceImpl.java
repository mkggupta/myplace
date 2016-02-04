package com.myplace.service.user.service.v1_0;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.user.util.UserUtils;
import com.myplace.common.util.EmailUtil;
import com.myplace.common.util.MyPlaceUtil;
import com.myplace.common.util.StorageUtil;
import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.media.MediaDAO;
import com.myplace.dao.modules.user.UserDAO;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.dto.ForgetPasswordVerification;
import com.myplace.dto.RegistrationInfo;
import com.myplace.dto.UserAuth;
import com.myplace.dto.UserEmailVerification;
import com.myplace.dto.UserFileInfo;
import com.myplace.dto.UserInfo;
import com.myplace.dto.UserStats;
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
	public UserInfo regLoginUser(RegistrationInfo registrationInfo,int appType) throws UserServiceFailedException,
	UserServiceValidationFailedException {
/*	public Long regLoginUser(RegistrationInfo registrationInfo,Map<String, Object> clientMap) throws UserServiceFailedException,
			UserServiceValidationFailedException {*/
		//UserServiceValidator.validateRegistrationRequest(registrationInfo);
		Long userId = null;
		UserInfo userInfo = null;
		try {
			if(registrationInfo.getRegistrationMode()==0){
				return null;
			}
			UserAuth userAuth = UserUtils.transformRegistrationInfoToUserAuth(registrationInfo, true);
			logger.debug("userAuth--"+userAuth);
			if(null!=userAuth){
				try {
					if(registrationInfo.getRegistrationMode()==4 || registrationInfo.getRegistrationMode()==5){
						logger.debug(registrationInfo.getUserName()+"userId--"+registrationInfo.getPassword());
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
					if(userAuth.getLatitude()>0.0 && userAuth.getLatitude()>0.0 ){
						logger.debug(userAuth.getLatitude()+"userId--"+userId+" getLongitude="+ userAuth.getLongitude()+" getLastLocation="+userAuth.getLastLocation());
						userDAO.updateUserLocation(userId, userAuth.getLatitude(), userAuth.getLongitude(), userAuth.getLastLocation())	;
					}
					userInfo = getUserProfile(userId);
					return userInfo;
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
						return null;
					}else{ 
					registrationInfo.setId(userDAO.saveUserAuth(userAuth));
					logger.debug("after saving userauth user id --"+registrationInfo.getId() +" userAuth ="+userAuth.getId());
					userId = registrationInfo.getId();
					userInfo = UserUtils.transformRegistrationInfoToUserInfo(registrationInfo);
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
						userInfo.setUserName(registrationInfo.getUserName());
						userInfo.setRegister(true);
						userInfo.setStatus(userAuth.getStatus());
						logger.debug("after saving userInfo  id ="+userAuth.getId());
						//saving stats but it should not stop user to register
						try {
							UserStats userStats = new UserStats();
							userStats.setUserId(userAuth.getId());
							userDAO.saveUserStats(userStats);
						} catch (Exception e1) {
							logger.error("Could not save stats for userid "+userAuth.getId());	
						}
						List<String> pImgUrls = new ArrayList<String>();
						//save user profile pic data
						if(null!= userInfo.getUserFileInfo() && userInfo.getUserFileInfo().size()>0){
							try {
								List<UserFileInfo> userFileInfoList = userInfo.getUserFileInfo();
								logger.debug("afteruserFileInfoList ="+userFileInfoList);
								for(UserFileInfo userFileInfo:userFileInfoList){
									userFileInfo.setUserId(userId);
									mediaDAO.saveUserFileInfo(userFileInfo);
									logger.debug("afteru saveUserFileInfo for  userId="+userId);
									pImgUrls.add(StorageUtil.getProfileImageUrl(userFileInfo));
								}
							} catch (Exception e) {
								//we are not throwing error as profile pic saving should not  interrupt user reg
								logger.error("problem in saving user pic for  userId="+userId +", error is "+ e.getLocalizedMessage());	
							}
						}else{
							logger.debug("else");
						   try {
							List<UserFileInfo> userFileInfoList =mediaDAO.getUserFileInfoByUserId(userAuth.getId());
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
							} catch (Exception e) {
								//we are not throwing error as profile pic saving should not  interrupt user reg
								logger.error("problem in getting user pic for  userId="+userId +", error is "+ e.getLocalizedMessage());	
							}
						}
						userInfo.setImgUrls(pImgUrls);
					}
					
					logger.debug("registrationInfo"+registrationInfo.getRegistrationMode()); 
					if (registrationInfo.getRegistrationMode()>0 && registrationInfo.getRegistrationMode()<4) {
						UserThirdPartyAuth userThirdPartyAuth = new UserThirdPartyAuth(userAuth.getId(), registrationInfo.getRegistrationMode(),
								registrationInfo.getUserKey(), registrationInfo.getAppKey());
						userDAO.saveUserThirdPartyAuth(userThirdPartyAuth);
					}else if (registrationInfo.getRegistrationMode()>3) {
						String verificationCode = MyPlaceUtil.generateRandomAlphanumericKey(32);
						logger.debug("verificationCode"+verificationCode); 
						// SEND Verification EMAIL
						try {
							UserEmailVerification userEmailVerification  = new UserEmailVerification();
							userEmailVerification.setUserId(userId);
							userEmailVerification.setEmailId( userAuth.getUserName());
							userEmailVerification.setStatus(MyPlaceConstant.NOT_VERIFIED_STATUS);
							userEmailVerification.setVerificationCode(verificationCode);
							long emailVerificationId = userDAO.saveUserEmailVerification(userEmailVerification);
							logger.debug("emailVerificationId :: "+emailVerificationId+" ,emailid : "+ userAuth.getUserName()+" ,verificationCode :: "+verificationCode);
							
							EmailUtil.sendEmailVerificationEmail(registrationInfo.getFirstName(), registrationInfo.getLastName(), emailVerificationId,
								userAuth.getUserName(), verificationCode);
						} catch (Exception e) {
							logger.error("Exception in sending verification email for userid" +userId, e);
							//EmailUtil.sendEmailVerificationEmail(registrationVO.getFirstName(), registrationVO.getLastName(), emailVerificationId,
							//		userAuth.getUserName(), verificaitonCode);
						}
					}
					//for web app
					 if(appType>3){
						if (userInfo.getStatus()==0){
							userInfo.setVerifyAccUrl(MyPlaceUtil.getVerifyAccountUIUrl());
						 }
						    userInfo.setChangePassUrl(MyPlaceUtil.getChangePassUIUrl());
							userInfo.setProfileUpdateUrl(MyPlaceUtil.getEditProfileUIUrl());
					 }
				}
				}
			}
		} catch (DataUpdateFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		return userInfo;
	}
	
	
	public UserInfo getUserProfile(long userId) throws UserServiceFailedException, UserServiceValidationFailedException{
		UserInfo userInfo = null;
		try {
			//String userName = userDAO.getUserNameById(userId);
			UserAuth userAuth = userDAO.getUserAuthDetailsByUserId(userId);
			logger.debug(userAuth+"  userAuth");
			if(null!=userAuth){
				userInfo= getUserInfoFromUserAuth(userAuth,userInfo);
			}
		} catch (DataAccessFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		
		return userInfo;
	}
	
	public UserInfo getUserPvtProfile(long userId,int appType) throws UserServiceFailedException, UserServiceValidationFailedException{
		UserInfo userInfo = null;
		try {
			//String userName = userDAO.getUserNameById(userId);
			UserAuth userAuth = userDAO.getUserAuthDetailsByUserId(userId);
			logger.debug(userAuth+"  userAuth");
			if(null!=userAuth){
				userInfo= getUserInfoFromUserAuth(userAuth,userInfo);
				 if(appType>3){
					 
						if (userInfo.getBussCnt()>0){
							userInfo.setBussListUrl(MyPlaceUtil.getMyBusinessListUrl(userInfo.getId()));
						}
						if (userInfo.getStatus()==0){
							userInfo.setVerifyAccUrl(MyPlaceUtil.getVerifyAccountUIUrl());
						}
						//userInfo.setChangePassUrl(MyPlaceUtil.getChangePassUIUrl());
						userInfo.setProfileUpdateUrl(MyPlaceUtil.getEditProfileUIUrl());
					}
			}
		} catch (DataAccessFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		
		return userInfo;
		
	}
	
	public UserInfo updateUser(UserInfo userInfo, int appType) throws UserServiceFailedException{
		try {
			UserInfo userInfoObj = null;
			try {
				userInfoObj = userDAO.getUserInfoByUserId(userInfo.getId());
				UserUtils.updateDBUserVO(userInfoObj, userInfo);
			} catch (DataAccessFailedException e) {
				if (null == userInfoObj || userInfoObj.getId() == 0) {
					logger.debug("User not found userId: " + userInfoObj);
					throw new UserServiceFailedException(ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION);
				}
			}
			try {
				userDAO.updateUser(userInfoObj);
				UserStats userStats = userDAO.getUserStats(userInfo.getId());
				logger.debug(userStats+" userStats");
				 if (null!=userStats){
					 userInfoObj.setBussCnt(userStats.getBussCnt());
				 }
				 UserAuth userAuth = userDAO.getUserAuthDetailsByUserId(userInfo.getId());
				 if (null!=userAuth){
					 userInfoObj.setStatus(userAuth.getStatus());
				 }
				 if(appType>3){
					
					if (userInfoObj.getBussCnt()>0){
						userInfoObj.setBussListUrl(MyPlaceUtil.getMyBusinessListUrl(userInfo.getId()));
					}
					if (userInfoObj.getStatus()==0){
						userInfoObj.setVerifyAccUrl(MyPlaceUtil.getVerifyAccountUIUrl());
					}
					userInfoObj.setChangePassUrl(MyPlaceUtil.getChangePassUIUrl());
					userInfoObj.setProfileUpdateUrl(MyPlaceUtil.getEditProfileUIUrl());
				}
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
						List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByType(userInfoObj.getGender());
						if(null!=defaultFileInfoList){
							for(DefaultFileInfo defaultFileInfo:defaultFileInfoList){
								pImgUrlsList.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));
							}	
						}
					}
				}
				if(null!= pImgUrlsList && pImgUrlsList.size()>0){
					userInfoObj.setImgUrls(pImgUrlsList);
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
	
	public boolean changePassword(long userId, String newPassword,String oldPassword)throws UserServiceFailedException{
		try {
			return userDAO.changePassword(userId, newPassword ,oldPassword);
		} catch (DataUpdateFailedException e) {
			logger.error("Exception in changePassword"+e.getLocalizedMessage(),e);
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public boolean verifyEmailAddress(long verificationId, String emailAddress, String verificationCode)throws UserServiceFailedException{
		boolean isChanged = false ;
		try {
				UserEmailVerification userEmailVerification  = new UserEmailVerification();
				userEmailVerification.setId(verificationId);
				//userEmailVerification.setUserId(userId);verificationId
				userEmailVerification.setEmailId(emailAddress);
				userEmailVerification.setStatus(MyPlaceConstant.VERIFIED_STATUS);
				userEmailVerification.setVerificationCode(verificationCode);
				isChanged = userDAO.updateUserEmailVerification(userEmailVerification);
				if(isChanged){
					userDAO.updateUserStatus(emailAddress, MyPlaceConstant.ACTIVE_STATUS);
				}
			
		} catch (DataUpdateFailedException e) {
			logger.error("Exception in verifyEmailAddress"+e.getLocalizedMessage(),e);
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		return isChanged;
	}
	
	public void forgetPasswordRequested(String userEmail)throws UserServiceFailedException{
		
		try {
			UserAuth userAuth =  userDAO.getUserAuthDetails(userEmail);
			if (null != userAuth) {
				logger.debug(userAuth.getStatus()+"---userAuth.getStatus()");
				if(userAuth.getStatus()==MyPlaceConstant.BLOCKED_STATUS){
					throw new UserServiceFailedException(ErrorCodesEnum.USER_IS_BLOCKED);
				}else{
					logger.debug(userAuth.getId()+"---userAuth.getStatus()");
					UserInfo userIno = userDAO.getUserInfoByUserId(userAuth.getId());
					logger.debug(userIno.getContactName()+"---userIno.getContactName()");
					String verificationCode = MyPlaceUtil.generateRandomAlphanumericKey(32);
					ForgetPasswordVerification forgetPasswordVerification = new ForgetPasswordVerification();
					forgetPasswordVerification.setUserId(userAuth.getId());
					forgetPasswordVerification.setVerificationCode(verificationCode);
					forgetPasswordVerification.setStatus(MyPlaceConstant.UNUSED_STATUS);
					long forgetPasswordId= userDAO.saveUserForgetPasswordVerification(forgetPasswordVerification);
					logger.debug(userIno.getContactName()+"---saveUserForgetPasswordVerification()"+forgetPasswordId);
					//send Email
					try {
						EmailUtil.sendResetPasswordEmail(userIno.getContactName(), userIno.getLastName(), forgetPasswordId, userAuth.getId(), userAuth.getUserName(),
								verificationCode);

					} catch (Exception e) {
						logger.error("Exception in sending forgetPasswordRequestedd email. Trying again ", e);
						throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
					}
				}
				
			}else{
				throw new UserServiceFailedException(ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION);
			}
		} catch (DataAccessFailedException |DataUpdateFailedException e) {
			logger.error("Exception in forgetPasswordRequested"+e.getLocalizedMessage(),e);
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		
	}
	
	

	public void verifyAccountRequested(String userEmail)throws UserServiceFailedException{
		
		try {
			UserAuth userAuth =  userDAO.getUserAuthDetails(userEmail);
			if (null != userAuth) {
				logger.debug(userAuth.getStatus()+"---userAuth.getStatus()");
				if(userAuth.getStatus()==MyPlaceConstant.BLOCKED_STATUS){
					throw new UserServiceFailedException(ErrorCodesEnum.USER_IS_BLOCKED);
				}else{
					logger.debug(userAuth.getId()+"---userAuth.getStatus()");
					UserInfo userIno = userDAO.getUserInfoByUserId(userAuth.getId());
					logger.debug(userIno.getContactName()+"---userIno.getContactName()");
					String verificationCode = MyPlaceUtil.generateRandomAlphanumericKey(32);
					UserEmailVerification userEmailVerification  = new UserEmailVerification();
					userEmailVerification.setUserId(userAuth.getId());
					userEmailVerification.setEmailId( userAuth.getUserName());
					userEmailVerification.setVerificationCode(verificationCode);
					userEmailVerification.setStatus(MyPlaceConstant.NOT_VERIFIED_STATUS);
					long emailVerificationId= userDAO.saveUserEmailVerification(userEmailVerification);
					logger.debug(userIno.getContactName()+"---saveUserForgetPasswordVerification()"+emailVerificationId);
					//send Email
					try {
						EmailUtil.sendEmailVerificationEmail(userIno.getContactName(), userIno.getLastName(), emailVerificationId,
								userAuth.getUserName(), verificationCode);
					} catch (Exception e) {
						logger.error("Exception in sending forgetPasswordRequestedd email. Trying again ", e);
						throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
					}
				}
				
			}else{
				throw new UserServiceFailedException(ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION);
			}
		} catch (DataAccessFailedException |DataUpdateFailedException e) {
			logger.error("Exception in verifyAccountRequested"+e.getLocalizedMessage(),e);
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		
	}
	
	   public boolean resetPassword(long userId, long forgotPasswordId, String verificationCode, String userName, String password) throws UserServiceFailedException{
		   	boolean isReset = false ;
		   	try {
		   		ForgetPasswordVerification forgetPasswordVerification = new ForgetPasswordVerification();
				forgetPasswordVerification.setUserId(userId);
				forgetPasswordVerification.setId(forgotPasswordId);
				forgetPasswordVerification.setVerificationCode(verificationCode);
				forgetPasswordVerification.setStatus(MyPlaceConstant.USED_STATUS);
				isReset = userDAO.updateUserForgetPasswordVerification(forgetPasswordVerification);
		   		if(isReset){
		   			userDAO.resetPassword(userId, userName, password);
		   		}
		   	} catch (DataUpdateFailedException e) {
				logger.error("Exception in resetPassword"+e.getLocalizedMessage(),e);
				throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
			}
			return isReset;
	   }

	   /*
	    * 
	    * @see com.myplace.service.user.service.v1_0.UserService#getUserPublicProfile(long, long)
	    * this is used to see users public profile
	    */
	   public UserInfo getUserPublicProfile(long userId,long visitorId) throws UserServiceFailedException, UserServiceValidationFailedException{
		   
		   UserInfo userInfo = getUserProfile(userId);
		   
		   return userInfo;
	   }
	   /*
	    *
	    * @see com.myplace.service.user.service.v1_0.UserService#getUserStatus(long)
	    * this method will return the user current status
	    */
	   public byte getUserStatus(long userId) throws UserServiceFailedException{
		   try {
				return userDAO.getUserStatus(userId);
			} catch (DataAccessFailedException e) {
				logger.error("Exception in getUserStatus"+e.getLocalizedMessage(),e);
				throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
			}
	   }
	   /*
	    * this is used to check user exist or not
	    * @see com.myplace.service.user.service.v1_0.UserService#findUserByUserNamePassword(java.lang.String, java.lang.String)
	    */
	   public UserInfo findUserByUserNamePassword (String userName, String password)throws UserServiceFailedException{
		    UserInfo userInfo = null;
		   try {
			   logger.debug(userName+"  findUserByUserNamePassword.password="+password);
				UserAuth userAuth = userDAO.getUserAuthDetailsByUserNamePassword(userName, password);
				logger.debug(userAuth+"  findUserByUserNamePassword.userAuth");
				if(null!=userAuth){
					userInfo= getUserInfoFromUserAuth(userAuth,userInfo);
					if (userInfo.getBussCnt()>0){
						userInfo.setBussListUrl(MyPlaceUtil.getMyBusinessListUrl(userInfo.getId()));
					}
					if (userInfo.getStatus()==0){
						userInfo.setVerifyAccUrl(MyPlaceUtil.getVerifyAccountUIUrl());
					}
					userInfo.setChangePassUrl(MyPlaceUtil.getChangePassUIUrl());
					userInfo.setProfileUpdateUrl(MyPlaceUtil.getEditProfileUIUrl());
				}
			} catch (DataAccessFailedException e) {
				throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
			}
			
			return userInfo;
	   }
	   
	   
	 /*
	  * this is used as helper method to convert userauth object into userinfo  
	  */
	 public UserInfo getUserInfoFromUserAuth(UserAuth userAuth, UserInfo userInfo) throws UserServiceFailedException{
		   
		   try {
			userInfo = userDAO.getUserInfoByUserId(userAuth.getId());
			UserUtils.transformUserAuthToUserInfo(userAuth,userInfo);
			logger.debug(userInfo.getLatitude()+" getLatitude");
			userInfo.setProfileUpdateUrl(MyPlaceUtil.getUpdateProfileApiUrl());
			UserStats userStats = userDAO.getUserStats(userAuth.getId());
			logger.debug(userStats+" userStats");
			if (null!=userStats){
				userInfo.setBussCnt(userStats.getBussCnt());
			}
			List<String> pImgUrls = new ArrayList<String>();
			List<UserFileInfo> userFileInfoList =mediaDAO.getUserFileInfoByUserId(userAuth.getId());
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
			userInfo.setImgUrls(pImgUrls);
		   } catch (DataAccessFailedException e) {
				throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
			}
		   
		   return userInfo;
	   }
}
