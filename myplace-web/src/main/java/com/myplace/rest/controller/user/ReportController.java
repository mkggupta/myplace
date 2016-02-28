package com.myplace.rest.controller.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.util.ControllerUtils;
import com.myplace.common.util.RequestProcessorUtil;
import com.myplace.dto.BusinessReportInfo;
import com.myplace.dto.ReportReasonInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.report.exception.ReportServiceException;
import com.myplace.service.report.service.v1_0.ReportService;





@Controller
@RequestMapping("/api/report")
public class ReportController {
	
	private static Logger logger = LoggerFactory.getLogger(ReportController.class);
	private ReportService reportService;
	
	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	
	@RequestMapping(value = "/pub/getreasonlist/{type}", method = RequestMethod.GET)
	public ModelAndView getReportReasonList(@PathVariable("type") byte type,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 Gson gson = new Gson();
		
		try {
			logger.error("getReportReasonList()"+MyPlaceConstant.BUSS_TYPE);
			List<ReportReasonInfo>  reportReasonList = reportService.getReportReasonList(MyPlaceConstant.BUSS_TYPE);
				 if(null!=reportReasonList && reportReasonList.size()>0){
					 	dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					    dataMap.put(MyPlaceWebConstant.REASONLIST, reportReasonList);
				 }else{
					   dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					   dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_REASON_SUCCESS.getSuccessMessage());
					   dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_REASON_SUCCESS.getSuccessCode());
				 }
		
		} catch (ReportServiceException e) {
			logger.error("getProfile()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		} catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
			String jsonData = gson.toJson(dataMap);
			modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
			modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
	
		logger.debug("verifyEmailAddress.dataMap="+dataMap);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/pub/reportbuss", method = RequestMethod.POST)
	public ModelAndView saveBussReportRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		int appType =0;
		 boolean isSuccess = false;
		try {
			if(null!=requestMap && requestMap.size()>0){
				if (null!=requestMap.get(MyPlaceWebConstant.APP_TYPE)){
					appType = Integer.parseInt(requestMap.get(MyPlaceWebConstant.APP_TYPE).toString());
				}
				
				BusinessReportInfo businessReportInfo = new BusinessReportInfo();
				RequestProcessorUtil.enrichBusinessReportInfoObj(requestMap, businessReportInfo);
				reportService.saveBusinessReportInfo(businessReportInfo);
				if(appType>3){
					isSuccess=true;
					modelAndView.addObject(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.BUSS_REPORT_SUCCESS.getSuccessMessage());
				}else{
				   dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
				   dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.BUSS_REPORT_SUCCESS.getSuccessMessage());
				   dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.BUSS_REPORT_SUCCESS.getSuccessCode());
				  
				}
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.PARAMETER_MISSING.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.PARAMETER_MISSING.getErrorCode());
				modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.PARAMETER_MISSING.getErrorMessage());
			}
			
			
		}catch (ReportServiceException e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
		}catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
		}
		if(appType>3){
			if (isSuccess){
				modelAndView.setViewName(MyPlaceWebConstant.REPORT_BUSINESS_SUCCESS);
			}else{
				modelAndView.setViewName(MyPlaceWebConstant.REPORT_BUSINESS_FAIL);
			}
		}else{ 
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("saveBussReportRequest.dataMap="+dataMap);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/getreportlist/{type}/{bid}", method = RequestMethod.GET)
	public ModelAndView getBussReportList(@PathVariable("type") byte type,@PathVariable("bid") long businessId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 Gson gson = new Gson();
		
		try {
			if(businessId>0){
			List<BusinessReportInfo>  businessReportInfoList = reportService.getBusinessReportInfoByBId(businessId);
				 if(null!=businessReportInfoList && businessReportInfoList.size()>0){
					 	dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					    dataMap.put(MyPlaceWebConstant.BUSINESS_REPORT_LIST, businessReportInfoList);
				 }else{
					   dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					   dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSS_REPORT_SUCCESS.getSuccessMessage());
					   dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSS_REPORT_SUCCESS.getSuccessCode());
				 }
			}else{
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
				dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.PARAMETER_MISSING.getErrorMessage());
				dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.PARAMETER_MISSING.getErrorCode());
			}
		} catch (ReportServiceException e) {
			logger.error("getBussReportList()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		} catch (Exception e) {
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
			String jsonData = gson.toJson(dataMap);
			modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
			modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
	
		logger.debug("getBussReportList.dataMap="+dataMap);
		return modelAndView;
		
	}
	
	/*
	 * This is used for web report UI load
	 */
	
	
	@RequestMapping(value = "/pub/getreport/{type}/{bid}/{uid}", method = RequestMethod.GET)
	public ModelAndView getReportUI(@PathVariable("type") byte type,@PathVariable("bid") String businessId,@PathVariable("uid") String userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			
			logger.debug("saveBussReportRequest.type="+type+","+businessId+","+userId);
			if(MyPlaceConstant.BUSS_TYPE==type){
				
			}else if(MyPlaceConstant.USER_TYPE==type){
				
			}else{
				type =MyPlaceConstant.BUSS_TYPE;
			}
			List<ReportReasonInfo>  reportReasonList = reportService.getReportReasonList(type);
			 if(null!=reportReasonList && reportReasonList.size()>0){
				 modelAndView.addObject(MyPlaceWebConstant.JSP_RESPONSE, reportReasonList);
			 }
			 if(StringUtils.isNotBlank(userId)){	
				 modelAndView.addObject(MyPlaceWebConstant.USERID, userId);
			 }
			
			 if(StringUtils.isNotBlank(businessId)  ){	
				 modelAndView.addObject(MyPlaceWebConstant.BUSINESS_ID, businessId);
			 }
			 modelAndView.addObject(MyPlaceWebConstant.REPORT_TYPE, type);
			
		} catch (Exception e) {
			logger.error("getReportUI()"+e.getLocalizedMessage(),e);
			 modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
		}
			modelAndView.setViewName(MyPlaceWebConstant.REPORT_BUSINESS);
		
		return modelAndView;
	}
	

}
