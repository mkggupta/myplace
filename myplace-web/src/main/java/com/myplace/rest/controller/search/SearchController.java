package com.myplace.rest.controller.search;

import java.util.HashMap;

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
import com.myplace.common.util.RequestProcessorUtil;
import com.myplace.dto.BusinessSearchVO;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.search.exception.SearchServiceException;
import com.myplace.service.search.service.v1_0.SearchService;


@Controller
@RequestMapping("/api/search")
public class SearchController {
	private static Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	private SearchService searchService ;
	
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	@RequestMapping(value = "/pub/getBuss", method = RequestMethod.POST)
	public ModelAndView getBusiness(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
				HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
				int startLimit = 0;
				int pageLimit = 20;
				if (requestMap != null && requestMap.size() > 0) {
					BusinessSearchVO businessSearchVO = RequestProcessorUtil.enrichSearchVO(requestMap);
					HashMap<String, Object> responseMap  = searchService.getBusinessSearch(businessSearchVO, startLimit, pageLimit);
					 if(null!=responseMap && responseMap.size()>0){
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.putAll(responseMap);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());	
				}
		
		} catch (SearchServiceException e) {
			logger.error("SearchController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("SearchController.getBusiness.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pub/getBuss/more", method = RequestMethod.POST)
	public ModelAndView getBusinessMore(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
				HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
				
				int startLimit = Integer.parseInt(httpServletRequest.getParameter(MyPlaceWebConstant.SLIMIT));
				int pageLimit = 20;
				if (requestMap != null && requestMap.size() > 0) {
					BusinessSearchVO businessSearchVO = RequestProcessorUtil.enrichSearchVO(requestMap);
					HashMap<String, Object> responseMap  = searchService.getBusinessSearch(businessSearchVO, startLimit, pageLimit);
					 if(null!=responseMap && responseMap.size()>0){
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.putAll(responseMap);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());	
				}
		
		} catch (SearchServiceException e) {
			logger.error("SearchController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("SearchController.getBusiness.dataMap="+dataMap);
		return modelAndView;
	}
	

}
