package com.myplace.service.user.service.v1_0;

import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dto.RegistrationInfo;
import com.myplace.dto.UserInfo;
import com.myplace.service.user.exception.DeviceRegFailedException;
import com.myplace.service.user.exception.UserServiceFailedException;
import com.myplace.service.user.exception.UserServiceValidationFailedException;


public interface UserService {
	 
	//public Long  regLoginUser(RegistrationInfo registrationInfo, Map<String, Object> clientMap)throws UserServiceFailedException, UserServiceValidationFailedException;
	public Long  regLoginUser(RegistrationInfo registrationInfo)throws UserServiceFailedException, UserServiceValidationFailedException;
	public UserInfo getUserProfile(long userId) throws UserServiceFailedException, UserServiceValidationFailedException;
	public UserInfo updateUser(UserInfo userInfo) throws UserServiceFailedException;
	public Long regPushDevice(UserPushInfo userPushInfo) throws DeviceRegFailedException,UserServiceFailedException;
	void updateUserPushStatus(long userId, String pushStatus) throws UserServiceFailedException;
	public boolean changePassword(long userId, String newPassword,String oldPassword)throws UserServiceFailedException;
	public boolean verifyEmailAddress(long verificationId, String emailAddress, String verificationCode)throws UserServiceFailedException;
	public void forgetPasswordRequested(String userEmail)throws UserServiceFailedException;
	public boolean resetPassword(long userId, long forgotPasswordId, String verificationCode, String userName, String password) throws UserServiceFailedException;
}
