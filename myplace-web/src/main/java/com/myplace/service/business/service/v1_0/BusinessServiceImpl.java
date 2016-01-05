package com.myplace.service.business.service.v1_0;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.common.business.util.BusinessUtil;
import com.myplace.common.util.StorageUtil;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.business.BusinessDAO;
import com.myplace.dto.BusinessFileInfo;
import com.myplace.dto.BusinessInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.business.exception.BusinessServiceException;



public class BusinessServiceImpl implements BusinessService {
	private static Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
	private BusinessDAO businessDAO ;
	
	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public Long createBusiness(BusinessInfo businessInfo) throws BusinessServiceException{
		
		logger.debug("BusinessServiceImpl----"+businessInfo.getBussName());
		Long businessId = null;
		try {
			businessId = businessDAO.saveBusinessInfo(businessInfo);
			logger.debug("businessId----"+businessId);
			if(null==businessId || businessId ==0){
				throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
			}
			
			 List<BusinessFileInfo> fileInfoList = businessInfo.getBusinessFileInfo();
			logger.debug("fileInfo----"+fileInfoList);
			if(null!=fileInfoList && fileInfoList.size()>0){
				for(BusinessFileInfo businessFileInfo :fileInfoList){
					businessFileInfo.setBussId(businessId);
					businessDAO.saveBusinessFileInfo(businessFileInfo);
				}
			}
		} catch (DataUpdateFailedException e) {
			logger.error("createBusiness---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		return businessId;
	}
	
	public List<BusinessInfo> getMyBusinessList (Long userId) throws BusinessServiceException{
		
	try {
		List<BusinessInfo> businessInfoList=businessDAO.getMyBusinessList(userId);
		List<Long> businessIdList = new ArrayList<Long>();
		for(BusinessInfo businessInfo : businessInfoList){
			businessIdList.add(businessInfo.getBussId());
		}
		if(null!=businessIdList && businessIdList.size()>0){
			logger.debug("businessIdList---"+businessIdList);
			List<BusinessFileInfo>  BusinessFileInfoList = businessDAO.getBusinessFileInfoList(businessIdList);
			logger.debug("BusinessFileInfoList---"+BusinessFileInfoList);
			for(BusinessFileInfo  businessFileInfo : BusinessFileInfoList){
				logger.debug("id---"+businessFileInfo.getBussId());
				for(BusinessInfo businessInfo : businessInfoList){
					logger.debug(businessInfo.getBussId()+" --- Busine---"+businessFileInfo.getBussId());
					if(businessInfo.getBussId()==businessFileInfo.getBussId()){
						businessInfo.setBussImageUrl(StorageUtil.getImageUrl(businessFileInfo));
						break;
					}
				}
				
			}
		}
		
		
			return businessInfoList;
		} catch (DataAccessFailedException e) {
			logger.error("getMyBusinessList---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public BusinessInfo getMyBusinessDetail (Long userId,Long businessId) throws BusinessServiceException{

	try {
			BusinessInfo  businessInfo = businessDAO.getMyBusinessDetail(userId,businessId);
			businessInfo.setBussImageUrl(StorageUtil.getImageUrl(businessDAO.getBusinessFileInfo(businessId)));
			return businessInfo;
		} catch (DataAccessFailedException e) {
			logger.error("getMyBusinessDetail---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public BusinessInfo getBusinessDetail (Long businessId) throws BusinessServiceException{

	try {
			BusinessInfo  businessInfo = businessDAO.getBusinessDetail(businessId);
			businessDAO.updateBusinessView(businessId);
			logger.debug("getBusinessDetail---businessId="+businessId);
			businessInfo.setBussImageUrl(StorageUtil.getImageUrl(businessDAO.getBusinessFileInfo(businessId)));
			return businessInfo;
		} catch (DataAccessFailedException ex) {
			logger.error("getBusinessDetail---"+ex.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}catch(DataUpdateFailedException e ){
			logger.error("getBusinessDetail---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}

	@Override
	public void changeBussStatus(long userId, long businessId, Byte status)throws BusinessServiceException {
		try {
			businessDAO.updateBussStatus(userId, businessId, status);
		} catch (DataUpdateFailedException e) {
			logger.error("changeBussStatus---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		
	}
	
	public BusinessInfo updateBusinessInfo (BusinessInfo businessInfo) throws BusinessServiceException{
		
		BusinessInfo businessInfoObj = null ;
		
		try {
			businessInfoObj = businessDAO.getBusinessDetail(businessInfo.getBussId());
			BusinessUtil.updateBusinessInfo(businessInfoObj, businessInfo);
		} catch (DataAccessFailedException e) {
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_NOT_FOUND_EXCEPTION);
		}
		try {
			businessDAO.updateBusinessDetail(businessInfoObj);
		} catch (DataUpdateFailedException e) {
			logger.error("Exception in updating user details in database for the businessInfoObj : " + businessInfoObj + " error  : " + e.getMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		return businessInfoObj;
	}
}
