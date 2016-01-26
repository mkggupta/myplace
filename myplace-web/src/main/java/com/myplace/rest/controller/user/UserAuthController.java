package com.myplace.rest.controller.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myplace.common.constant.UserParameters;
import com.myplace.common.util.ControllerUtils;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.user.exception.UserServiceFailedException;
import com.myplace.service.user.service.v1_0.UserService;





@Controller
@RequestMapping("/api/usrauth")
public class UserAuthController {
	
	private static Logger logger = LoggerFactory.getLogger(UserAuthController.class);
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/verifyemail/{id}/{emailId}/{code}", method = RequestMethod.GET)
	public ModelAndView verifyEmailAddress(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@PathVariable("id") long verificationId, @PathVariable("emailId") String emailId, @PathVariable("code") String verificationCode) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 String jsonData = null;
		 Gson gson = new Gson();
		 boolean status = false;
		try {
			
			logger.debug("userId() nothing  "+verificationId+" emailId="+emailId+" verificationCode="+verificationCode);
				 status = userService.verifyEmailAddress(verificationId, emailId, verificationCode);
				 if(status){
					    dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.EMAIL_VARIFICATION_SUCCESS.getSuccessMessage());
					    dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.EMAIL_VARIFICATION_SUCCESS.getSuccessCode());
				 }else{
					   dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					   dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.EMAIL_ALREADY_VARIFICATION_SUCCESS.getSuccessMessage());
					   dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.EMAIL_ALREADY_VARIFICATION_SUCCESS.getSuccessCode());
				 }
		
		} catch (UserServiceFailedException e) {
			logger.error("getProfile()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.INVALID_EMAIL_VERIFICATION_CREDENTIALS_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.INVALID_EMAIL_VERIFICATION_CREDENTIALS_EXCEPTION.getErrorCode());
			
		} catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		
		if(status){
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
		    jsonData = gson.toJson(dataMap);
		}else{
		if (dataMap.size() == 0) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());	
		}
			jsonData = gson.toJson(dataMap);
		}
		
		if (status) {
			modelAndView.setViewName(MyPlaceWebConstant.EMAIL_VERIFICATION_SUCCESS_VIEW_NAME);
		} else {
			modelAndView.setViewName(MyPlaceWebConstant.EMAIL_VERIFICATION_FAILURE_VIEW_NAME);
		}
		//modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("verifyEmailAddress.dataMap="+dataMap);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/forgetpasswordrequest", method = RequestMethod.POST)
	public ModelAndView forgetPasswordRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		String userEmail= null;
		try {
			if(null!=requestMap && requestMap.size()>0){
				if (null != requestMap.get(UserParameters.USER_EMAIL)) {
					userEmail = requestMap.get(UserParameters.USER_EMAIL).toString();
					logger.debug("forgetPasswordRequest.userEmail="+userEmail);
					userService.forgetPasswordRequested(userEmail);
					 dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.FORGET_PASSWORD_EMAIL_SUCCESS.getSuccessMessage());
					 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.FORGET_PASSWORD_EMAIL_SUCCESS.getSuccessCode());
					
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_EMAIL_MISSING.getErrorMessage());
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_EMAIL_MISSING.getErrorCode());
				}
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_EMAIL_MISSING.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_EMAIL_MISSING.getErrorCode());
			}
			
		}catch (UserServiceFailedException e) {
			 if (ErrorCodesEnum.USER_IS_BLOCKED.getErrorCode().equalsIgnoreCase(e.getErrorCode())) {
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_IS_BLOCKED.getErrorMessage());
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_IS_BLOCKED.getErrorCode());
					logger.info("Exception in authentication : user is blocked : username : " + userEmail);

				}else if (ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode().equalsIgnoreCase(e.getErrorCode())) {
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
					
				}else{
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.AUTHENTICATION_SERVICE_FAILED_EXCEPTION.getErrorMessage());
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.AUTHENTICATION_SERVICE_FAILED_EXCEPTION.getErrorCode());
				}
				logger.error("Exception in request for change password request : userName : " + userEmail + " error " + e.getMessage(), e);
			
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("updateProfile.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		String userName = null;
		String password = null;
		long forgotPasswordId = 0;
		long userId = 0;
		String verificationCode = null;
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		boolean isSuccess = false ;
		try {
			if(null!=requestMap && requestMap.size()>0){	
				if (null != requestMap.get(UserParameters.USER_NAME)) {
					userName = requestMap.get(UserParameters.USER_NAME).toString();
				} else {
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USERNAME_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USERNAME_MISSING.getErrorMessage());
				}

				if (null != requestMap.get(UserParameters.PASSWORD)) {
					password = requestMap.get(UserParameters.PASSWORD).toString();
					//password = EncryptionFactory.getEncrypter(EncryptionAlgoEnum.MD5).encrypt(password);
				} else {
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.PASSWORD_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.PASSWORD_MISSING.getErrorMessage());
				}
				if (null != requestMap.get(UserParameters.FORGOT_PASSWORD_ID)) {
					forgotPasswordId = Long.parseLong(requestMap.get(UserParameters.FORGOT_PASSWORD_ID).toString());
				} else {
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FORGOT_PASSWORD_ID_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FORGOT_PASSWORD_ID_MISSING.getErrorMessage());
				}
				if (null != requestMap.get(UserParameters.USER_ID)) {
					userId = Long.parseLong(requestMap.get(UserParameters.USER_ID).toString());
				} else {			
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USERID_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USERID_MISSING.getErrorMessage());
				}
				if (null != requestMap.get(UserParameters.VERIFICAATION_CODE)) {
					verificationCode = requestMap.get(UserParameters.VERIFICAATION_CODE).toString();
				} else {
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.VERIFICTION_CODE_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.VERIFICTION_CODE_MISSING.getErrorMessage());
				}
				isSuccess = userService.resetPassword(userId, forgotPasswordId, verificationCode, userName, password);
				if (isSuccess){
				 dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
				 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.RESET_PASSWORD_SUCCESS.getSuccessMessage());
				 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.RESET_PASSWORD_SUCCESS.getSuccessCode());
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_PASSWORD_NOT_CHANGE.getErrorMessage());
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_PASSWORD_NOT_CHANGE.getErrorCode());
				}
			}
			
		} catch (UserServiceFailedException e) {
			logger.error("updateProfile()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		Gson gson = new Gson();
		if(!isSuccess){
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
		}
		String jsonData = gson.toJson(dataMap);
		if (isSuccess) {
			modelAndView.setViewName(MyPlaceWebConstant.PASSWORD_CHANGE_SUCCESS_VIEW_NAME);
		} else {
			modelAndView.setViewName(MyPlaceWebConstant.PASSWORD_CHANGE_FAILURE_VIEW_NAME);
		}
		//modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("updateProfile.dataMap="+dataMap);
		return modelAndView;
	}
	

}
