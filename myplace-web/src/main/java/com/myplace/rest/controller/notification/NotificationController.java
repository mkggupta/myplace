package com.myplace.rest.controller.notification;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.myplace.dto.NotificationMessage;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.notification.exception.NotificationServiceException;
import com.myplace.service.notification.service.v1_0.NotificationService;

@Controller
@RequestMapping("/api/notif")
public class NotificationController {
	private static Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	private NotificationService notificationService ;
	
	@Autowired
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping(value = "/pvt/my/{userId}", method = RequestMethod.GET)
	public ModelAndView getNotification(@PathVariable long userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		if(logger.isDebugEnabled()){
			logger.debug("NotificationController.getNotification"+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("NotificationController.getNotification"+requestParamMap);
		}
		try {

				if(userId>0 ){
					List<NotificationMessage> notifList = notificationService.getNotificationList(userId);
					 if(null!=notifList && notifList.size()>0){
						 dataMap.put(MyPlaceWebConstant.NOTIFICATION_LIST, notifList);
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessCode());	
				}
		
		} catch (NotificationServiceException e) {
			logger.error("NotificationController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("NotificationController.getNotification.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pub/getnotif", method = RequestMethod.GET)
	public ModelAndView getPublicNotification(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		if(logger.isDebugEnabled()){
			logger.debug("NotificationController.getNotification"+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("NotificationController.getNotification"+requestParamMap);
		}
		try {
					List<NotificationMessage> notifList = notificationService.getPublicNotificationList();
					 if(null!=notifList && notifList.size()>0){
						 dataMap.put(MyPlaceWebConstant.NOTIFICATION_LIST, notifList);
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessCode());	
					}
				
		
		} catch (NotificationServiceException e) {
			logger.error("NotificationController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("NotificationController.getNotification.dataMap="+dataMap);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/pvt/delete/{userId}/{notifId}", method = RequestMethod.GET)
	public ModelAndView getDeleteNotification(@PathVariable long userId,@PathVariable long notifId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		if(logger.isDebugEnabled()){
			logger.debug("NotificationController.getNotification"+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("NotificationController.getDeleteNotification"+requestParamMap);
		}
		try {
			if(userId>0 && notifId >0 ){
						logger.debug("NotificationController.userId"+userId +" notifId="+notifId);
				        notificationService.deleteUserNotification(userId,notifId);
					    dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NOTIF_DELETE_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NOTIF_DELETE_SUCCESS.getSuccessCode());	
					    dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
			}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessCode());	
			}
			
		} catch (NotificationServiceException e) {
			logger.error("NotificationController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.NOTIFICATION_DELETE_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.NOTIFICATION_DELETE_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("NotificationController.getDeleteNotification.dataMap="+dataMap);
		return modelAndView;
	}
	@RequestMapping(value = "/pvt/deleteall/{userId}", method = RequestMethod.GET)
	public ModelAndView getDeleteAllNotification(@PathVariable long userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		if(logger.isDebugEnabled()){
			logger.debug("NotificationController.getNotification"+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("NotificationController.getDeleteNotification"+requestParamMap);
		}
		try {
			if(userId>0 ){
						logger.debug("NotificationController.userId"+userId );
				        notificationService.deleteAllUserNotification(userId);
					    dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NOTIF_DELETE_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NOTIF_DELETE_SUCCESS.getSuccessCode());	
					    dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
			}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_NOTIF_SUCCESS.getSuccessCode());	
			}
			
		} catch (NotificationServiceException e) {
			logger.error("NotificationController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.NOTIFICATION_DELETE_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.NOTIFICATION_DELETE_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("NotificationController.getDeleteNotification.dataMap="+dataMap);
		return modelAndView;
	}

}
