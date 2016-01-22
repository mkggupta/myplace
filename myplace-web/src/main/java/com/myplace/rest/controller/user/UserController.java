package com.myplace.rest.controller.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.constant.UserParameters;
import com.myplace.common.util.ClientHeaderUtil;
import com.myplace.common.util.ControllerUtils;
import com.myplace.common.util.RequestProcessorUtil;
import com.myplace.dto.UserInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.user.exception.UserServiceFailedException;
import com.myplace.service.user.exception.UserServiceValidationFailedException;
import com.myplace.service.user.service.v1_0.UserService;


@Controller
@RequestMapping("/api/usr")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/pvt/profile/{userId}", method = RequestMethod.GET)
	public ModelAndView getProfile(@PathVariable String userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 String jsonData = null;
		 Gson gson = new Gson();
		try {
			if(StringUtils.isNotBlank(userId)){	
				UserInfo userInfo = userService.getUserProfile(Long.parseLong(userId));
				 if(null!= userInfo){
					 dataMap.put(MyPlaceWebConstant.USER_DETAIL, userInfo);
				 }else{
					 logger.debug("getProfile() nothing");
					   dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					   dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
					   dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
				 }
			}
		} catch (UserServiceFailedException e) {
			logger.error("getProfile()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			
		} catch (UserServiceValidationFailedException e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE,ErrorCodesEnum.getErrorCodesEnum(e.getErrorCode()).getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, e.getErrorCode());
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		
		if(null!= userId){
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
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("getProfile.dataMap="+dataMap);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/pvt/updateprofile", method = RequestMethod.POST)
	public ModelAndView updateProfile(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				UserInfo userInfo = new UserInfo();
				RequestProcessorUtil.enrichUserVO(requestMap, userInfo, null);
				if(userInfo.getId()>0){
					userInfo = userService.updateUser(userInfo);
					 if(null!= userInfo){
						 dataMap.put(MyPlaceWebConstant.USER_DETAIL, userInfo);
						 dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					 }else{
						 logger.debug("updateProfile() ");
						   dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
							dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
							dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
					}
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
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("updateProfile.dataMap="+dataMap);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/pvt/changepassword", method = RequestMethod.POST)
	public ModelAndView changePassword(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		String newPassword = null;
		String confirmPassword = null;
		String oldPassword = null;
		boolean isError = false;
		try {
			long userId = ClientHeaderUtil.extractUserIdFromHeader(httpServletRequest);
		if(userId>0){
			HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
			if(null!=requestMap && requestMap.size()>0){
	
				if (null != requestMap.get(UserParameters.OLD_PASSWORD) && StringUtils.isNotBlank(requestMap.get(UserParameters.OLD_PASSWORD).toString())) {
					oldPassword = requestMap.get(UserParameters.OLD_PASSWORD).toString();
				} else {
					logger.debug("isError1---"+isError);
					isError = true;
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.OLD_PASSWORD_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.OLD_PASSWORD_MISSING.getErrorMessage());
				}

				if (null != requestMap.get(UserParameters.PASSWORD) && StringUtils.isNotBlank(requestMap.get(UserParameters.PASSWORD).toString())) {
					newPassword = requestMap.get(UserParameters.PASSWORD).toString();
				} else {
					logger.debug("isError2---"+isError);
					isError = true;
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.PASSWORD_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.PASSWORD_MISSING.getErrorMessage());
				}
				
				if (null != requestMap.get(UserParameters.CONFIRM_PASSWORD) && StringUtils.isNotBlank(requestMap.get(UserParameters.CONFIRM_PASSWORD).toString())) {
					confirmPassword = requestMap.get(UserParameters.CONFIRM_PASSWORD).toString();
				} else {
					logger.debug("isError3---"+isError);
					isError = true;
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.CONFIRM_PASSWORD_MISSING.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.CONFIRM_PASSWORD_MISSING.getErrorMessage());
				}
				
				if(null!=newPassword && null!=oldPassword && newPassword.equals(oldPassword)){
					isError = true;
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_CHANGE_PASSWORD_SAME.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_CHANGE_PASSWORD_SAME.getErrorMessage());
				}
				
				if(null!=newPassword && null!=oldPassword && !newPassword.equals(confirmPassword)){
					isError = true;
					dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_PASSWORD_MISMATCH.getErrorCode());
					dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_PASSWORD_MISMATCH.getErrorMessage());
				}
				
				if (!isError) {
					logger.debug("isError4---"+isError);
					boolean isSucceed = userService.changePassword(userId, newPassword, oldPassword);
					logger.debug("isSucceed---"+isSucceed);
					if(isSucceed){
						 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.PASSWORD_CHANGE_SUCCESS.getSuccessMessage());
						 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.PASSWORD_CHANGE_SUCCESS.getSuccessCode());	
						 dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
						dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_PASSWORD_NOT_CHANGE.getErrorMessage());
						dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_PASSWORD_NOT_CHANGE.getErrorCode());
					}
				}
			}
		}else{
				isError = true;
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_INFO_MISSING.getErrorCode());
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_INFO_MISSING.getErrorMessage());
			}
			
		} catch (UserServiceFailedException e) {
			logger.error("changePassword()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			
		}catch (Exception e) {
			logger.error("changePassword11()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("change password.dataMap="+dataMap);
		return modelAndView;
	}
	@RequestMapping(value = "/pub/pubprofile/{userId}", method = RequestMethod.GET)
	public ModelAndView getPublicProfile(@PathVariable String userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 String jsonData = null;
		 Gson gson = new Gson();
		try {
			if(StringUtils.isNotBlank(userId)){	
				UserInfo userInfo = userService.getUserPublicProfile(Long.parseLong(userId),Long.parseLong(userId));
				 if(null!= userInfo){
					 dataMap.put(MyPlaceWebConstant.USER_DETAIL, userInfo);
				 }else{
					 logger.debug("getPublicProfile() nothing "+userId);
					   dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					   dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
					   dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
				 }
			}
		} catch (UserServiceFailedException e) {
			logger.error("getProfile()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			
		} catch (UserServiceValidationFailedException e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE,ErrorCodesEnum.getErrorCodesEnum(e.getErrorCode()).getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, e.getErrorCode());
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		
		if(null!= userId){
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
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("getProfile.dataMap="+dataMap);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/pvt/status/{userId}", method = RequestMethod.GET)
	public ModelAndView getUserStatus(@PathVariable String userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {	
			if(StringUtils.isNotBlank(userId)){
					logger.debug("userId---"+userId);
					byte userStatus = userService.getUserStatus(Long.parseLong(userId));
					logger.debug("userStatus---"+userStatus);
					if(MyPlaceConstant.INACTIVE_STATUS==userStatus){
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.USER_INACTIVE_STATUS_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.USER_INACTIVE_STATUS_SUCCESS.getSuccessCode());	
					}else if(MyPlaceConstant.ACTIVE_STATUS==userStatus){
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.USER_ACTIVE_STATUS_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.USER_ACTIVE_STATUS_SUCCESS.getSuccessCode());	
						
					}else{
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.USER_BOLCKED_STATUS_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.USER_BOLCKED_STATUS_SUCCESS.getSuccessCode());	
					}
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			}
		
		} catch (Exception e) {
			logger.error("getUserStatus()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("getUserStatus.dataMap="+dataMap);
		return modelAndView;
	}

}
