/**
 * 
 */
package com.myplace.service.business.validation;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.common.util.DateTimeUtils;
import com.myplace.dto.RegistrationInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.user.exception.UserServiceValidationFailedException;


/**
 * @author Manish
 * 
 */
public class BusinessServiceValidator {
	private static Logger logger = LoggerFactory.getLogger(BusinessServiceValidator.class);

	public static void validateRegistrationRequest(RegistrationInfo registerationVO) throws UserServiceValidationFailedException {
		if (null == registerationVO) {
			logger.error("registerationVO is null ");
			throw new UserServiceValidationFailedException(ErrorCodesEnum.USER_SERVICE_VALIDATION_FAILED_EXCEPTION);

		} else {

			if (null == registerationVO.getUserName()) {
				logger.error("user name is null ");
				throw new UserServiceValidationFailedException(ErrorCodesEnum.USERNAME_MISSING);
			}

			if (null == registerationVO.getContactName()) {
				logger.error(" first name is null for registration mode : " + registerationVO.getRegistrationMode() + " username : "
						+ registerationVO.getUserName());
				throw new UserServiceValidationFailedException(ErrorCodesEnum.FIRST_NAME_MISSING);
			}
			
			if(registerationVO.getGender() == 0 ){
				logger.error(" user gender is zero for registration mode : "+registerationVO.getRegistrationMode()+ " username : "
		               + registerationVO.getUserName());
				throw new UserServiceValidationFailedException(ErrorCodesEnum.USER_GENDER_MISSING);
			}
			
			if (registerationVO.getRegistrationMode() > 0) {

				if (null == registerationVO.getUserKey()) {
					logger.error(" partner user key is null for registration mode : " + registerationVO.getRegistrationMode() + " username : "
							+ registerationVO.getUserName());
					throw new UserServiceValidationFailedException(ErrorCodesEnum.PARTNER_USER_KEY_MISSING);
				}
				if (null == registerationVO.getAppKey()) {
					logger.error(" partner app key is null for registration mode : " + registerationVO.getRegistrationMode() + " username : "
							+ registerationVO.getUserName());
					throw new UserServiceValidationFailedException(ErrorCodesEnum.APP_ID_MISSING);
				}
			} else {
				if (null == registerationVO.getPassword()) {
					logger.error(" password is null for registration mode : " + registerationVO.getRegistrationMode() + " username : "
							+ registerationVO.getUserName());
					throw new UserServiceValidationFailedException(ErrorCodesEnum.PASSWORD_MISSING);
				}
				if (null == registerationVO.getDob()) {
					logger.error(" dob name is null for registration mode : " + registerationVO.getDob() );
					throw new UserServiceValidationFailedException(ErrorCodesEnum.USER_DOB_MISSING);
				}else{
					Date userDob = registerationVO.getDob();
					/*if(DateTimeUtils.getAge(userDob) < 13){
						logger.error("User dob  : "+ userDob +" , user age :"+DateTimeUtils.getAge(userDob) );
						throw new UserServiceValidationFailedException(ErrorCodesEnum.USER_DOB_INCORRECT);
					}*/
				}
			}
		}

	}

}
