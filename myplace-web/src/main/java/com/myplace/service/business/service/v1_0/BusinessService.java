package com.myplace.service.business.service.v1_0;

import java.util.List;

import com.myplace.dto.BusinessInfo;
import com.myplace.service.business.exception.BusinessServiceException;

public interface BusinessService {
	public Long createBusiness(BusinessInfo businessInfo) throws BusinessServiceException;
	public List<BusinessInfo> getMyBusinessList (Long userId) throws BusinessServiceException;
	public BusinessInfo getMyBusinessDetail (Long userId,Long businessId,int appType) throws BusinessServiceException;
	public BusinessInfo getBusinessDetail (Long businessId,int appType) throws BusinessServiceException;
	public void changeBussStatus(long userId,long businessId,Byte status) throws BusinessServiceException;
	public BusinessInfo updateBusinessInfo (BusinessInfo businessInfo ,int appType) throws BusinessServiceException;
	public Long sendPush(long businessId) throws BusinessServiceException;
	
}
