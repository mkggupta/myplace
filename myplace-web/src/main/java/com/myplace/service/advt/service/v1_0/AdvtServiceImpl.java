package com.myplace.service.advt.service.v1_0;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.util.DateTimeUtils;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.advt.AdvtDAO;
import com.myplace.dto.AdvertisementDTO;
import com.myplace.dto.AdvertisementInfo;
import com.myplace.dto.AdvtStats;
import com.myplace.dto.AdvtTemplate;
import com.myplace.dto.FileInfo;
import com.myplace.dto.PaymentInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.framework.success.SuccessCodesEnum;
import com.myplace.rest.constant.MyPlaceWebConstant;
import com.myplace.service.advt.exception.AdvtServiceException;


public class AdvtServiceImpl implements AdvtService {
	private static Logger logger = LoggerFactory.getLogger(AdvtServiceImpl.class);
	private AdvtDAO advtDAO ;
	public void setAdvtDAO(AdvtDAO advtDAO) {
		this.advtDAO = advtDAO;
	}
	public Long createAdvt(AdvertisementDTO advertisementDTO) throws AdvtServiceException{
		
		logger.debug("AdvtServiceImpl----"+advertisementDTO);
		Long advtCode = null;
		try {
			if(StringUtils.isNotBlank(advertisementDTO.getAdvertisementInfo().getStartTime())){
				String startTime=advertisementDTO.getAdvertisementInfo().getStartTime().toLowerCase(); 
				if(startTime.contains("am") || startTime.contains("pm")  ){
					startTime = startTime.replace(" am", ":00");
					startTime = startTime.replace(" pm", ":00");
				}
				
				if(StringUtils.isNotBlank(advertisementDTO.getAdvertisementInfo().getStartDate())){
					advertisementDTO.getAdvertisementInfo().setStartDate(advertisementDTO.getAdvertisementInfo().getStartDate()+" "+startTime);
				}
				if(StringUtils.isNotBlank(advertisementDTO.getAdvertisementInfo().getEndDate())){
					advertisementDTO.getAdvertisementInfo().setEndDate(advertisementDTO.getAdvertisementInfo().getEndDate()+" "+startTime);
				}
			}else{
				advertisementDTO.getAdvertisementInfo().setStartDate(DateTimeUtils.format(new Date(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
				advertisementDTO.getAdvertisementInfo().setEndDate(DateTimeUtils.format(new Date(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
			}
			
			 advtCode = advtDAO.saveAdvtInfo(advertisementDTO.getAdvertisementInfo());
			logger.debug("advtCode----"+advtCode);
			if(null==advtCode || advtCode ==0){
				throw new AdvtServiceException(ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION);
			}
			AdvertisementInfo advertisementInfo =advertisementDTO.getAdvertisementInfo();
			advertisementInfo.setAdvtCode(advtCode);
			advtDAO.saveAdvtDetail(advertisementInfo);
			logger.debug("saveAdvtDetail----"+advtCode);
			FileInfo fileInfo = advertisementDTO.getFileInfo();
			logger.debug("fileInfo----"+fileInfo);
			if(null!=fileInfo && fileInfo.getFile_name()!=null){
				fileInfo.setAdvtCode(advtCode);
				advtDAO.saveAdvtFileInfo(fileInfo);
				logger.debug("saveAdvtFileInfo----"+advtCode);
			}
			
			PaymentInfo paymentInfo = advertisementDTO.getPaymentInfo();
			if(null!=paymentInfo && paymentInfo.getCard_number()!=null){
				paymentInfo.setAdvtCode(advtCode);
				paymentInfo.setUser_id(advertisementInfo.getCreatorId());
				advtDAO.savePaymentInfo(paymentInfo);
				logger.debug("savePaymentInfo----"+advtCode);
			}
			
			
		} catch (DataUpdateFailedException e) {
			logger.error("createAdvt---"+e.getLocalizedMessage());
			throw new AdvtServiceException(ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION);
		}
		return advtCode;
	}
	
	public List<AdvtTemplate> getAdvtTemplate(Byte type) throws AdvtServiceException{
		
		try {
		  return advtDAO.getAdvtTemplate(type);
		} catch (DataAccessFailedException e) {
			throw new AdvtServiceException(ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION);
		}
	}
	@Override
	public void changeAdvtStatus(long userId,long advtCode, Byte status)throws AdvtServiceException {
	
		try {
			//int updateStatus = advtDAO.updateAdvtStatus(userId, advtCode, status);
			 advtDAO.updateAdvtStatus(userId, advtCode, status);
		} catch (DataUpdateFailedException e) {
			throw new AdvtServiceException(ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION);
		}
	}
	@Override
	public HashMap<String, Object> getAdvtDetails(Long userId, Long advtCode) throws AdvtServiceException {
		return null;
	}
	
	@Override
	public HashMap<String, Object> getAdvtList(Long userId, int startLimit,int endLimit, boolean order) throws AdvtServiceException {
		List<AdvertisementInfo> advList = new ArrayList<AdvertisementInfo>();
		HashMap<String, Object> advtMap = new HashMap<String, Object>();
		String feedType= MyPlaceConstant.ADVT_FEED;
		try {
			advList = advtDAO.getMyAdvtList(userId, startLimit, endLimit);
			if(advList== null || advList.size()==0){
				advtMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
				advtMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
			}else{
				advtMap.put(feedType, advList);
			}
		} catch (DataAccessFailedException e) {
			throw new AdvtServiceException(ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION);
		}
		return advtMap;
	}
	
	@Override
	public HashMap<String, Object> getAdvtStatsList(Long userId,Long advtCode) throws AdvtServiceException{
		List<AdvtStats> advStatsList = new ArrayList<AdvtStats>();
		HashMap<String, Object> advtMap = new HashMap<String, Object>();
		String feedType= MyPlaceConstant.ADVT_STATS;
		List<Long> listOfAdvts = new ArrayList<Long>();
		try {
			StringBuilder advtIds = new StringBuilder();;
			logger.debug("getAdvtStatsList---"+userId+" advtCode= "+advtCode);
			if(null==advtCode || advtCode==0){
				listOfAdvts = advtDAO.getAdvtListByUserId(userId);
				for(Long advt: listOfAdvts){
					advtIds.append(advt).append(",");
				}
			}else{
				advtIds.append(advtCode).append(",");
			}
			logger.debug("getAdvtStatsList advtIds---"+advtIds);
			if(advtIds.length()>0){
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("advtIds", advtIds.deleteCharAt(advtIds.length()-1).toString());
				advStatsList =advtDAO.getAdvtStatsList(parameterMap);
			}
			
			if(advStatsList== null || advStatsList.size()==0){
				advtMap.put(MyPlaceWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
				advtMap.put(MyPlaceWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
			}else{
				advtMap.put(feedType, advStatsList);
			}
		} catch (DataAccessFailedException e) {
			throw new AdvtServiceException(ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION);
		}
		return advtMap;
		
	}
}
