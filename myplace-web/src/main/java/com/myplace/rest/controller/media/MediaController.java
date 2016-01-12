package com.myplace.rest.controller.media;

import java.util.HashMap;

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
import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.util.ControllerUtils;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.media.service.v1_0.MediaService;


@Controller
@RequestMapping("/api/media")
public class MediaController {
	private static Logger logger = LoggerFactory.getLogger(MediaController.class);
	
	private MediaService mediaService ;
	@Autowired
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	@RequestMapping(value = "/pvt/create", method = RequestMethod.POST)
	public ModelAndView createDefaultPic(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = ControllerUtils.getImageRequestMapFromMultipart(httpServletRequest);
	
		try { 
			if(null!=requestMap && requestMap.size()>0){
				
				DefaultFileInfo defaultFileInfo = (DefaultFileInfo)requestMap.get(MyPlaceConstant.DEFAULT_FILE_DATA);
				if(null!=requestMap.get(MyPlaceConstant.ID)){
					defaultFileInfo.setId(Integer.parseInt(requestMap.get(MyPlaceConstant.ID).toString()));
				}
				if(null!=requestMap.get(MyPlaceConstant.MEDIA_TYPE)){
					defaultFileInfo.setType(Integer.parseInt(requestMap.get(MyPlaceConstant.MEDIA_TYPE).toString()));
				}
				mediaService.saveDefaultMedia(defaultFileInfo);
				dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_SUCCESS);
			}
				
		} catch (Exception e) {
			logger.error("MediaController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyPlaceWebConstant.STATUS, MyPlaceWebConstant.STATUS_ERROR);	
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyPlaceWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyPlaceWebConstant.RESPONSE, jsonData);
		logger.debug("MediaController.dataMap="+dataMap);
		return modelAndView;
	}
	

}
