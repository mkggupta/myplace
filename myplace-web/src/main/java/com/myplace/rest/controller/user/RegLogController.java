package com.myplace.rest.controller.user;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myplace.common.constant.UserParameters;
import com.myplace.common.util.ClientHeaderUtil;
import com.myplace.common.util.ClientInfo;
import com.myplace.common.util.ControllerUtils;
import com.myplace.common.util.RequestProcessorUtil;
import com.myplace.dto.RegistrationInfo;
import com.myplace.dto.UserInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.user.exception.UserServiceFailedException;
import com.myplace.service.user.exception.UserServiceValidationFailedException;
import com.myplace.service.user.service.v1_0.UserService;






@Controller
@RequestMapping("/api/user")
public class RegLogController {
	
	private static Logger logger = LoggerFactory.getLogger(RegLogController.class);
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/pub/register", method = RequestMethod.POST)
	public ModelAndView registerLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 Long userId = null;
		 String jsonData = null;
		 Gson gson = new Gson();
		 int appType =0;
		 boolean isSuccess = false;
		 UserInfo userInfo = null;
		if(logger.isDebugEnabled()){
			logger.debug("RegLogController.registerLogin= "+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));
			
			}
			logger.debug("RegLogController.create"+requestParamMap);
		}
		
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				RegistrationInfo registrationInfo = new RegistrationInfo();
				//Map<String, Object> clientParamMap = ClientHeaderUtil.extractClientParam(httpServletRequest);
				 //RequestProcessorUtil.enrichRegistrationInfo(requestMap,registrationInfo,clientParamMap);
				 //userId = userService.regLoginUser(registrationInfo,clientParamMap);
				ClientInfo clientInfo= ClientHeaderUtil.extractClientHeaderParam(httpServletRequest);
				RequestProcessorUtil.enrichRegistrationInfoObj(requestMap,registrationInfo,clientInfo);
			     userInfo = userService.regLoginUser(registrationInfo);
				 if(null!= userInfo){
					 logger.debug(registrationInfo.getRegistrationMode()+" --RegLogController.userInfo =="+userInfo.isRegister());
					 dataMap.put(MyPlaceWebConstant.USER_DETAIL, userInfo);
					 userId=userInfo.getId();
					 if(userInfo.isRegister() && registrationInfo.getRegistrationMode()<4){
						 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.THIRD_REG_SUCCESS.getSuccessMessage());
						 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.THIRD_REG_SUCCESS.getSuccessCode());	
					 }else if(userInfo.isRegister() && registrationInfo.getRegistrationMode()>3){
						 isSuccess =true;
						 if(appType>3){
							 modelAndView.addObject(MyPlaceWebConstant.JSP_RESPONSE, userInfo);
						 }else{
							 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.APP_REG_SUCCESS.getSuccessMessage());
							 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.APP_REG_SUCCESS.getSuccessCode());
						 }
					 }else if(!userInfo.isRegister()){
						 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.LOGIN_SUCCESS.getSuccessMessage());
						 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.LOGIN_SUCCESS.getSuccessCode());	
					 }
				 }else{
					 if(appType>3){
						  modelAndView.addObject(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.LOG_REG_ALREADY_EXIST.getSuccessMessage());
					 }else{
						 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.LOG_REG_ALREADY_EXIST.getSuccessMessage());
						 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.LOG_REG_ALREADY_EXIST.getSuccessCode());	
					 }
				 }
			}
		} catch (UserServiceFailedException e) {
			logger.error("RegLogController()"+e.getLocalizedMessage(),e);
			 if(appType>3){
				  modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			 }else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
			 }
			
		} catch (UserServiceValidationFailedException e) {
			 if(appType>3){
				  modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.getErrorCodesEnum(e.getErrorCode()).getErrorMessage());
			 }else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE,ErrorCodesEnum.getErrorCodesEnum(e.getErrorCode()).getErrorMessage() );
				dataMap.put(MyPlaceWebConstant.CODE,e.getErrorCode());
			 }
		}catch (Exception e) {
			 if(appType>3){
				  modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			 }else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
			 }
		}

		if(null!= userId){
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
		    jsonData = gson.toJson(dataMap);
		}else{
		if (dataMap.size() == 0) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		
		jsonData = gson.toJson(dataMap);
		}
		if(appType>3){
			if (isSuccess){
				modelAndView.setViewName(MyPlaceWebConstant.USER_PROFILE);
			}else{
				modelAndView.setViewName(MyPlaceWebConstant.REGISTER);
			}
		}else{
			modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
			modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		}
		logger.debug("RegLogController.dataMap="+dataMap);
		return modelAndView;
	
	}

	/*
	 * This method is currently used for web login
	 */
	@RequestMapping(value = "/pub/login", method = RequestMethod.POST)
	public ModelAndView userLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 String jsonData = null;
		 Gson gson = new Gson();
		 UserInfo userInfo = null;
		 int appType =0;
		 boolean isSuccess = false;
		
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				if (null!=requestMap.get(MyPlaceWebConstant.APP_TYPE)){
					appType = Integer.parseInt(requestMap.get(MyPlaceWebConstant.APP_TYPE).toString());
				}
				if (null!=requestMap.get(UserParameters.PASSWORD) && null!=requestMap.get(UserParameters.USER_NAME)){
					String password =requestMap.get(UserParameters.PASSWORD).toString();
					String userName = requestMap.get(UserParameters.USER_NAME).toString();
					if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)){
						 userInfo= userService.findUserByUserNamePassword(userName, password);
						 isSuccess = true;
						 if(appType>3){
							 modelAndView.addObject(MyPlaceWebConstant.JSP_RESPONSE, userInfo);
						 }else{
							 dataMap.put(MyPlaceWebConstant.USER_DETAIL, userInfo);
							 dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						 }
						 
					}else{
						//wrong parameter
						if(appType>3){
						    modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_PASSWORD_WRONG.getErrorMessage());
						}else{
						    dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
							dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_PASSWORD_WRONG.getErrorMessage());
							dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_PASSWORD_WRONG.getErrorCode());
						}
					}
					
				}else{
					//parameter is missing
					if(appType>3){
					    modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.PARAMETER_MISSING.getErrorMessage());
					}else{
					    dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
						dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.PARAMETER_MISSING.getErrorMessage());
						dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.PARAMETER_MISSING.getErrorCode());
					}
				}

			}
		} catch (UserServiceFailedException e) {
			logger.error("RegLogController()"+e.getLocalizedMessage(),e);
			if(appType>3){
			    modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
			}
		}catch (Exception e) {
			if(appType>3){
			    modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
			}
		}
		if(appType>3){
			if (isSuccess){
				modelAndView.setViewName(MyPlaceWebConstant.USER_PROFILE);
				//modelAndView.addObject(MyPlaceWebConstant.JSP_RESPONSE, userInfo);
			}else{
				modelAndView.setViewName(MyPlaceWebConstant.LOGIN);
			}
		}else{
			jsonData = gson.toJson(dataMap);
			modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		}
		logger.debug("RegLogController.appType="+appType);
		return modelAndView;
	
	}

}
