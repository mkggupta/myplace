package com.myplace.service.report.service.v1_0;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.report.ReportDAO;
import com.myplace.dto.BusinessReportInfo;
import com.myplace.dto.ReportReasonInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.report.exception.ReportServiceException;


public class ReportServiceImpl implements ReportService {
	private static Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	private ReportDAO reportDAO ;
	
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	
	@Override
	public List<ReportReasonInfo> getReportReasonList(byte type) throws ReportServiceException{
		
		try {
			List<ReportReasonInfo> reportReasonInfoList ;
			logger.debug("getReportReasonList from type ="+type);
			reportReasonInfoList = reportDAO.getReportReasonList(type);
		    return reportReasonInfoList;
		} catch (DataAccessFailedException e) {
			logger.error("getReportReasonList  error= "+e.getLocalizedMessage());
			throw new ReportServiceException(ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION);
		}
	}
	@Override
	public void saveBusinessReportInfo(BusinessReportInfo businessReportInfo) throws ReportServiceException{
	 try {
			reportDAO.saveBusinessReportInfo(businessReportInfo);
		} catch (DataUpdateFailedException e) {
			throw new ReportServiceException(ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	@Override
	public List<BusinessReportInfo> getBusinessReportInfoByBId(long businessId) throws ReportServiceException{
		try {
			List<BusinessReportInfo> businessReportInfoList ;
			logger.debug("getBusinessReportInfoByBId from businessId ="+businessId);
			businessReportInfoList = reportDAO.getBusinessReportInfoByBId(businessId);
		    return businessReportInfoList;
		} catch (DataAccessFailedException e) {
			logger.error("getBusinessReportInfoByBId  error= "+e.getLocalizedMessage());
			throw new ReportServiceException(ErrorCodesEnum.REPORT_SERVICE_FAILED_EXCEPTION);
		}
	}
	
}
