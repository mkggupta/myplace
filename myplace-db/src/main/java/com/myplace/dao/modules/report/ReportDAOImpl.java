package com.myplace.dao.modules.report;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.constants.ReportConstant;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.base.AbstractDBManager;
import com.myplace.dto.BusinessReportInfo;
import com.myplace.dto.ReportReasonInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;



public class ReportDAOImpl extends AbstractDBManager implements ReportDAO {
	private static Logger logger = LoggerFactory.getLogger(ReportDAOImpl.class);
	
	
	@SuppressWarnings("unchecked")
	public List<ReportReasonInfo> getReportReasonList(byte type) throws DataAccessFailedException{
		try {
			     logger.debug("type=="+ type);
			    return (List<ReportReasonInfo>) sqlMapClient_.queryForList(ReportConstant.GET_REPORT_REASON_LIST,type);
			}catch(SQLException e){
				logger.error("Exception in getReportReasonList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessReportInfo> getBusinessReportInfoByBId(long businessId) throws DataAccessFailedException {
		try {
		       return (List<BusinessReportInfo>) sqlMapClient_.queryForList(ReportConstant.GET_REPORT_BUSINESS_LIST_BY_BUSINESS_ID,businessId);
		} catch (SQLException e) {
			logger.error("Exception in getBusinessReportInfoByBId : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public void saveBusinessReportInfo(BusinessReportInfo businessReportInfo)throws DataUpdateFailedException{
	 try {
			sqlMapClient_.insert(ReportConstant.SAVE_BUSINESS_REPORT_INFO,businessReportInfo);
		} catch (SQLException e) {
			logger.error("Exception in saveBusinessReportInfo : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	

	
}
