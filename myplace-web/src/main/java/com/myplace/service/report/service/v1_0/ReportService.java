package com.myplace.service.report.service.v1_0;

import java.util.List;

import com.myplace.dto.BusinessReportInfo;
import com.myplace.dto.ReportReasonInfo;
import com.myplace.service.report.exception.ReportServiceException;

public interface ReportService {
	
	
	public List<ReportReasonInfo> getReportReasonList(byte type) throws ReportServiceException;
	
	public void saveBusinessReportInfo(BusinessReportInfo businessReportInfo) throws ReportServiceException;
	
	public List<BusinessReportInfo> getBusinessReportInfoByBId(long businessId) throws ReportServiceException;
	
}
