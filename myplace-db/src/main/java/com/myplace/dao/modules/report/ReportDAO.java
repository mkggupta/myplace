package com.myplace.dao.modules.report;



import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.BusinessReportInfo;
import com.myplace.dto.ReportReasonInfo;

public interface ReportDAO {
	
	public List<ReportReasonInfo> getReportReasonList(byte type) throws DataAccessFailedException;
	public void saveBusinessReportInfo(BusinessReportInfo businessReportInfo)throws DataUpdateFailedException;
	public List<BusinessReportInfo> getBusinessReportInfoByBId(long businessId) throws DataAccessFailedException;
	
}
