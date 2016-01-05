package com.myplace.dao.modules.business;



import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.BusinessFileInfo;
import com.myplace.dto.BusinessInfo;

public interface BusinessDAO {
	public Long saveBusinessInfo(BusinessInfo businessInfo) throws DataUpdateFailedException;
	public void saveBusinessFileInfo(BusinessFileInfo businessFileInfo) throws DataUpdateFailedException;
	public BusinessFileInfo getBusinessFileInfo (Long businessId) throws DataAccessFailedException;
	public List<BusinessFileInfo> getBusinessFileInfoList (List<Long> businessIdList) throws DataAccessFailedException;
	public List<BusinessInfo> getMyBusinessList (Long userId) throws DataAccessFailedException;
	public BusinessInfo getMyBusinessDetail (Long userId,Long businessId) throws DataAccessFailedException;
	public BusinessInfo getBusinessDetail (Long businessId) throws DataAccessFailedException;
	public int updateBussStatus(Long userId,Long businessId,byte status) throws DataUpdateFailedException;
	public void updateBusinessDetail (BusinessInfo businessInfo) throws DataUpdateFailedException;
	public void updateBusinessView (Long businessId) throws DataUpdateFailedException;
}
