package com.myplace.rest.controller.stats;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myplace.common.util.ControllerUtils;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.advt.exception.AdvtServiceException;
import com.myplace.service.advt.service.v1_0.AdvtService;

@Controller
@RequestMapping("/api/stats")
public class StatsController {
	private static Logger logger = LoggerFactory.getLogger(StatsController.class);
	
	private AdvtService advtService ;
	
	public void setAdvtService(AdvtService advtService) {
		this.advtService = advtService;
	}
	
	@RequestMapping(value = "/pvt/getAdvtStats", method = RequestMethod.GET)
	public ModelAndView getAdvtStats(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		Long userId =0l;
		if(logger.isDebugEnabled()){
			logger.debug("StatsController.getAdvtStats"+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("StatsController.getAdvtStats"+requestParamMap);
		}
		try {
				userId = ControllerUtils.extractUserIdFromHeader(httpServletRequest);
				Long advtCode =(null!=httpServletRequest.getParameter(MyPlaceWebConstant.ADVTCODE))?Long.parseLong(httpServletRequest.getParameter(MyPlaceWebConstant.ADVTCODE)):0;
				if(userId>0){
					dataMap = advtService.getAdvtStatsList(userId,advtCode);
					 if(null!=dataMap && dataMap.size()>0){
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
				}
		
		} catch (AdvtServiceException e) {
			logger.error("StatsController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("StatsController.getAdvtStats.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/savestats", method = RequestMethod.POST)
	public ModelAndView saveAppStats(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		Long userId =0l;
		if(logger.isDebugEnabled()){
			logger.debug("StatsController.saveAppStats"+httpServletRequest);
			@SuppressWarnings("unchecked")
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("StatsController.saveAppStats"+requestParamMap);
		}
		try {
				userId = ControllerUtils.extractUserIdFromHeader(httpServletRequest);
				Long advtCode =(null!=httpServletRequest.getParameter(MyPlaceWebConstant.ADVTCODE))?Long.parseLong(httpServletRequest.getParameter(MyPlaceWebConstant.ADVTCODE)):0;
				if(userId>0){
					dataMap = advtService.getAdvtStatsList(userId,advtCode);
					 if(null!=dataMap && dataMap.size()>0){
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
				}
		
		} catch (AdvtServiceException e) {
			logger.error("StatsController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("StatsController.getAdvtStats.dataMap="+dataMap);
		return modelAndView;
	}
	

}
