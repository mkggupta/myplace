package com.myplace.rest.controller.search;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myplace.common.constant.MyPlaceBusinessConstant;
import com.myplace.common.util.ControllerUtils;
import com.myplace.common.util.RequestProcessorUtil;
import com.myplace.dto.BusinessSearchDTO;
import com.myplace.dto.BusinessSearchVO;
import com.myplace.dto.CategoryDTO;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.cat.service.v1_0.CatService;
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
	
	private CatService catService ;
	
	@Autowired
	public void setCatService(CatService catService) {
		this.catService = catService;
	}

	@RequestMapping(value = "/pub/getBuss", method = RequestMethod.POST)
	public ModelAndView getBusiness(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		int appType =0;
		 boolean isSuccess = false;
		 BusinessSearchVO businessSearchVO = new BusinessSearchVO();
		try {
				HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
				int startLimit = 0;
				int pageLimit = 20;
				if (requestMap != null && requestMap.size() > 0) {
					if (null!=requestMap.get(MyPlaceWebConstant.APP_TYPE)){
						appType = Integer.parseInt(requestMap.get(MyPlaceWebConstant.APP_TYPE).toString());
					}
					
					businessSearchVO = RequestProcessorUtil.enrichSearchVO(requestMap);
					HashMap<String, Object> responseMap  = searchService.getBusinessSearch(businessSearchVO, startLimit, pageLimit);
					 if(null!=responseMap && responseMap.size()>0){
						 isSuccess = true;
						 if(appType>3){
							 @SuppressWarnings("unchecked")
							List<BusinessSearchDTO> bussSearchList = (List<BusinessSearchDTO>) responseMap.get("searchResult");
							 logger.debug("SearchController.bussSearchList="+bussSearchList);
							 modelAndView.addObject(MyPlaceWebConstant.JSP_SEARCH_RESPONSE, bussSearchList); 
							 if(null!=responseMap.get(MyPlaceBusinessConstant.NEXTURL)){
								 businessSearchVO.setNextUrl(responseMap.get(MyPlaceBusinessConstant.NEXTURL).toString());
							 }
							
						 }else{ 
							 dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
							 dataMap.putAll(responseMap);
						 }
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());	
						modelAndView.addObject(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
					}
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());
					modelAndView.addObject(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
				}
		
		} catch (SearchServiceException e) {
			logger.error("SearchController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorCode());
			modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorMessage());
		}
		logger.debug("SearchController.getBusiness.appType="+appType+" isSuccess="+isSuccess);
		if(appType>3){
			
			 modelAndView.addObject(MyPlaceWebConstant.JSP_SEARCH_CRITERION, businessSearchVO); 
			 //return category list
			 try {
					String countryCode =(null!=httpServletRequest.getParameter(MyPlaceWebConstant.COUNTRY_CODE))?httpServletRequest.getParameter(MyPlaceWebConstant.COUNTRY_CODE):MyPlaceWebConstant.DEFAULT_COUNTRY_CODE;
					if(StringUtils.isNotBlank(countryCode)){
						List<CategoryDTO> catList = catService.getCategoryList(countryCode);
						logger.debug("SearchController.catList="+catList);
						 if(null!=catList && catList.size()>0){
							 modelAndView.addObject(MyPlaceWebConstant.JSP_CATEGORY, catList);
						 }
					}
				} catch (Exception e) {
					logger.error("getBusiness.."+e.getLocalizedMessage(),e);
				}
			
				modelAndView.setViewName(MyPlaceWebConstant.SEARCH);	
			
		}else{
			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMap);
			modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
			modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
			logger.debug("SearchController.getBusiness.dataMap="+dataMap);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/pub/getBuss/more", method = RequestMethod.POST)
	public ModelAndView getBusinessMore(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		int appType =0;
		BusinessSearchVO businessSearchVO = new BusinessSearchVO();
		try {
				HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
				
				int startLimit = Integer.parseInt(httpServletRequest.getParameter(MyPlaceWebConstant.SLIMIT));
				 logger.debug("SearchController.bussSearchList startLimit="+startLimit);
				int pageLimit = 20;
				if (requestMap != null && requestMap.size() > 0) {
					if (null!=requestMap.get(MyPlaceWebConstant.APP_TYPE)){
						appType = Integer.parseInt(requestMap.get(MyPlaceWebConstant.APP_TYPE).toString());
					}
					 businessSearchVO = RequestProcessorUtil.enrichSearchVO(requestMap);
					HashMap<String, Object> responseMap  = searchService.getBusinessSearch(businessSearchVO, startLimit, pageLimit);
					 if(null!=responseMap && responseMap.size()>0){
						 if(appType>3){
							 @SuppressWarnings("unchecked")
							List<BusinessSearchDTO> bussSearchList = (List<BusinessSearchDTO>) responseMap.get("searchResult");
							 logger.debug("SearchController.bussSearchList="+bussSearchList);
							 modelAndView.addObject(MyPlaceWebConstant.JSP_SEARCH_RESPONSE, bussSearchList); 
							 if(null!=responseMap.get(MyPlaceBusinessConstant.NEXTURL)){
								 businessSearchVO.setNextUrl(responseMap.get(MyPlaceBusinessConstant.NEXTURL).toString());
							 }
							
						 }else{  
							 dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
							 dataMap.putAll(responseMap);
						 }
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());
						modelAndView.addObject(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
					}
				}else{
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
					dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessCode());	
					modelAndView.addObject(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
				}
		
		} catch (SearchServiceException e) {
			logger.error("SearchController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorCode());
			modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			
		}
		if(appType>3){
			 modelAndView.addObject(MyPlaceWebConstant.JSP_SEARCH_CRITERION, businessSearchVO); 
			 //return category list
			 try {
					String countryCode =(null!=httpServletRequest.getParameter(MyPlaceWebConstant.COUNTRY_CODE))?httpServletRequest.getParameter(MyPlaceWebConstant.COUNTRY_CODE):MyPlaceWebConstant.DEFAULT_COUNTRY_CODE;
					if(StringUtils.isNotBlank(countryCode)){
						List<CategoryDTO> catList = catService.getCategoryList(countryCode);
						logger.debug("SearchController.catList="+catList);
						 if(null!=catList && catList.size()>0){
							 modelAndView.addObject(MyPlaceWebConstant.JSP_CATEGORY, catList);
						 }
					}
				} catch (Exception e) {
					logger.error("getBusinessMore()"+e.getLocalizedMessage(),e);
				}
			 
				modelAndView.setViewName(MyPlaceWebConstant.SEARCH);	
		}else{
			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMap);
			modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
			modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
			logger.debug("SearchController.getBusiness.dataMap="+dataMap);
		}
		return modelAndView;
	}
	/*
	 * This is used for web search
	 */
	
	@RequestMapping(value = "/pub/getsearch", method = RequestMethod.GET)
	public ModelAndView getSearchUI(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		 boolean isSuccess = false;
		 BusinessSearchVO businessSearchVO = new BusinessSearchVO();
		 businessSearchVO.setType("1");
		 modelAndView.addObject(MyPlaceWebConstant.JSP_SEARCH_CRITERION, businessSearchVO); 
		try {
			String countryCode =(null!=httpServletRequest.getParameter(MyPlaceWebConstant.COUNTRY_CODE))?httpServletRequest.getParameter(MyPlaceWebConstant.COUNTRY_CODE):MyPlaceWebConstant.DEFAULT_COUNTRY_CODE;
			if(StringUtils.isNotBlank(countryCode)){
				List<CategoryDTO> catList = catService.getCategoryList(countryCode);
				 if(null!=catList && catList.size()>0){
					 isSuccess= true;
					 modelAndView.addObject(MyPlaceWebConstant.JSP_CATEGORY, catList);
				 }else{
					 logger.debug("getSearchUI() no category");
					   modelAndView.addObject(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_CAT_SUCCESS.getSuccessMessage());	
				 }
			}
		} catch (Exception e) {
			logger.error("SearchController()"+e.getLocalizedMessage(),e);
			 modelAndView.addObject(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION.getErrorMessage());
		}
		if (isSuccess){
			modelAndView.setViewName(MyPlaceWebConstant.SEARCH);
		}else{
			modelAndView.setViewName(MyPlaceWebConstant.SEARCH);
		}
	
		return modelAndView;
	}
	
	
}
