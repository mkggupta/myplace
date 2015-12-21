package com.myplace.service.user.service.v1_0;

import java.util.Map;

import com.myplace.dto.RegistrationInfo;
import com.myplace.dto.UserInfo;
import com.myplace.service.user.exception.UserServiceFailedException;
import com.myplace.service.user.exception.UserServiceValidationFailedException;


public interface UserService {
	 
	public Long  regLoginUser(RegistrationInfo registrationInfo, Map<String, Object> clientMap)throws UserServiceFailedException, UserServiceValidationFailedException;
	public UserInfo getUserProfile(long userId) throws UserServiceFailedException, UserServiceValidationFailedException;
	public UserInfo updateUser(UserInfo userInfo) throws UserServiceFailedException;
}
