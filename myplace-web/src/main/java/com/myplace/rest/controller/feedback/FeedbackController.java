package com.myplace.rest.controller.feedback;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myplace.common.business.util.BusinessControllerUtils;
import com.myplace.common.constant.FeedBackWebConstant;
import com.myplace.common.util.RequestProcessorUtil;
import com.myplace.dto.FeedBackInfo;
import com.myplace.dto.FeedBackReplyInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.feedback.exception.FeedBackServiceException;
import com.myplace.service.feedback.service.v1_0.FeedBackService;

@Controller
@RequestMapping("/api/feedback")
public class FeedbackController {
	private static Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	
	private FeedBackService feedBackService ;
	
	public void setFeedBackService(FeedBackService feedBackService) {
		this.feedBackService = feedBackService;
	}

	@RequestMapping(value = "/pub/getfbacks", method = RequestMethod.GET)
	public ModelAndView getFeedBacks(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
	
		try {
			int startLimit = 0;
			int pageLimit = 10;
				
			 		List<FeedBackInfo> feedBackList = feedBackService.getFeedBackList(startLimit,pageLimit);
					 if(null!=feedBackList && feedBackList.size()>0){
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.FEEDBACK,feedBackList);
					}else{
						dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
						dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_FEEDBACK_SUCCESS.getSuccessMessage());
						dataMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_FEEDBACK_SUCCESS.getSuccessCode());	
					}
		
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.getCategories.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/savefbacks", method = RequestMethod.POST)
	public ModelAndView saveFeedBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
					FeedBackInfo feedBackInfo = new FeedBackInfo();
					feedBackInfo = RequestProcessorUtil.enrichFeedBackInfoInfo(requestMap, feedBackInfo);
			 	    feedBackService.saveFeedBackInfo(feedBackInfo);
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().saveFeedBack"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.saveFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/replyfbacks", method = RequestMethod.POST)
	public ModelAndView replyFeedBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
				    FeedBackReplyInfo feedBackReplyInfo = new FeedBackReplyInfo();
				    feedBackReplyInfo = RequestProcessorUtil.enrichFeedBackReplyInfo(requestMap, feedBackReplyInfo);
			 	    feedBackService.saveFeedBackReplyInfo(feedBackReplyInfo);
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().replyFeedBack"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.replyFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/delfback", method = RequestMethod.POST)
	public ModelAndView deleteFeedBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
				 	FeedBackInfo feedBackInfo = new FeedBackInfo();
					feedBackInfo = RequestProcessorUtil.enrichFeedBackInfoInfo(requestMap, feedBackInfo);
					feedBackInfo.setStatus(FeedBackWebConstant.FEEDBACK_DELETE_STATUS);
			 	    feedBackService.changeFeedBackStatus(feedBackInfo);
			 	    dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.DELETE_FEEDBACK_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().deleteFeedBack"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.deleteFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/pvt/delreplyfbacks", method = RequestMethod.POST)
	public ModelAndView deleteReplyFeedBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
				    FeedBackReplyInfo feedBackReplyInfo = new FeedBackReplyInfo();
				    feedBackReplyInfo = RequestProcessorUtil.enrichFeedBackReplyInfo(requestMap, feedBackReplyInfo);
				    feedBackReplyInfo.setStatus(FeedBackWebConstant.REPLY_FEEDBACK_DELETE_STATUS);
				    feedBackService.changeFeedBackReplyInfoStatus(feedBackReplyInfo);
				    dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.DELETE_REPLY_FEEDBACK_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().replyFeedBack"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.replyFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/pvt/changefbackstatus", method = RequestMethod.POST)
	public ModelAndView changeFeedBackStatus(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
				 	FeedBackInfo feedBackInfo = new FeedBackInfo();
					feedBackInfo = RequestProcessorUtil.enrichFeedBackInfoInfo(requestMap, feedBackInfo);
			 	    feedBackService.changeFeedBackStatus(feedBackInfo);
			 	    dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.CHANGE_FEEDBACK_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().changeFeedBackStatus"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.deleteFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/pvt/changereplyfbackstatus", method = RequestMethod.POST)
	public ModelAndView changeReplyFeedBackStatus(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
				    FeedBackReplyInfo feedBackReplyInfo = new FeedBackReplyInfo();
				    feedBackReplyInfo = RequestProcessorUtil.enrichFeedBackReplyInfo(requestMap, feedBackReplyInfo);
				    feedBackService.changeFeedBackReplyInfoStatus(feedBackReplyInfo);
				    dataMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.CHANGE_FEEDBACK_REPLY_SUCCESS.getSuccessMessage());
					dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().replyFeedBack"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);
			dataMap.put(MyPlaceWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyPlaceWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.replyFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	

}
