package com.myplace.rest.controller.push;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.myplace.dao.entities.UserPushInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.business.service.v1_0.BusinessService;
import com.myplace.service.user.exception.DeviceRegFailedException;
import com.myplace.service.user.service.v1_0.UserService;


@Controller
@RequestMapping("/api/push")
public class PushController {
private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private BusinessService businessService ;
	@Autowired
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}
	private static Logger logger = LoggerFactory.getLogger(PushController.class);
	
	@RequestMapping(value = "/pub/regDevice", method = RequestMethod.POST)
	public ModelAndView registerDevice(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 Long userId = null;
		 String jsonData = null;
		 Gson gson = new Gson();
		if(logger.isDebugEnabled()){
			logger.debug("PushController.registerDevice="+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));
			}
			logger.debug("PushController.registerDevice ="+requestParamMap);
		}
		
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				UserPushInfo userPushInfo = new UserPushInfo();
				//Map<String, Object> clientParamMap = ClientHeaderUtil.extractClientParam(httpServletRequest);
				ClientInfo clientInfo= ClientHeaderUtil.extractClientHeaderParam(httpServletRequest);
				//RequestProcessorUtil.enrichUserPushInfo(requestMap,userPushInfo,clientParamMap);
				 RequestProcessorUtil.enrichUserPushInfo(requestMap,userPushInfo,clientInfo);
				 userId = userService.regPushDevice(userPushInfo);
				 if(null!= userId && userId>0){
					 dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.LOG_DEVICE_REG_SUCCESS.getSuccessMessage());
					 dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.LOG_DEVICE_REG_SUCCESS.getSuccessCode());	
				 }else if(userId==-1){
					 dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
					 dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());	
				 }else{
					 	dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
						dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_DEVICE_PUSH_REG_EXCEPTION.getErrorMessage());
						dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_DEVICE_PUSH_REG_EXCEPTION.getErrorCode());
				 }
			}
		} catch (DeviceRegFailedException e) {
			logger.error("PushController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_DEVICE_PUSH_REG_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_DEVICE_PUSH_REG_EXCEPTION.getErrorCode());
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.USER_DEVICE_PUSH_REG_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.USER_DEVICE_PUSH_REG_EXCEPTION.getErrorCode());
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
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("PushController.dataMap="+dataMap);
		return modelAndView;
	
	}
	
	@RequestMapping(value = "/pub/status", method = RequestMethod.POST)
	public ModelAndView updateUserPushStatus(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
			if(null!=requestMap && requestMap.size()>0){
				if(null!=requestMap.get(UserParameters.UID) && null!=requestMap.get(UserParameters.PUSH_STATUS) ){
					Long userId = Long.parseLong(requestMap.get(UserParameters.UID).toString());
					String pushStatus = requestMap.get(UserParameters.PUSH_STATUS).toString();
					userService.updateUserPushStatus(userId, pushStatus);
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.SUCCESS_CODE);
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
					logger.error("Exception while updateUserPushStatus= "+requestMap);
				}
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				logger.error("Exception while updateUserPushStatus "+requestMap);
			}
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			logger.error("Exception while updateUserPushStatus "+e.getLocalizedMessage(),e);
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		logger.debug("PushController :: jsonData :: " + jsonData);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		return modelAndView;

	}
	
	@RequestMapping(value = "/pub/sendpush", method = RequestMethod.POST)
	public ModelAndView sendPush(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		try {
	
			//HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
			HashMap<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put("BusinessId", "88932");
			if(null!=requestMap && requestMap.size()>0){
				//if(null!=requestMap.get(UserParameters.UID) && null!=requestMap.get(UserParameters.PUSH_STATUS) ){
					Long businessId = Long.parseLong(requestMap.get("BusinessId").toString());
					//String pushStatus = requestMap.get(UserParameters.PUSH_STATUS).toString();
					businessService.sendPush(businessId);
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.SUCCESS_CODE);
				
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				logger.error("Exception while updateUserPushStatus "+requestMap);
			}
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			logger.error("Exception while updateUserPushStatus "+e.getLocalizedMessage(),e);
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		logger.debug("PushController :: jsonData :: " + jsonData);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		return modelAndView;

	}
}
