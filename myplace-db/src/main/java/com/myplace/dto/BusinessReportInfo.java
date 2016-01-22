package com.myplace.dto;

import java.util.Date;

public class BusinessReportInfo {
	
	 private Long businessId;
	 private Long reporterId;
	 private String reporterMail;
	 private String comment; 
	 private String reporterPhone;
	private Date reportTime;
	 private Long reportReasonId;
	 private Long reportReasonText;
	 private String reporterName;
	 private Byte status ;
	 
	 public String getReporterPhone() {
			return reporterPhone;
		}
		public void setReporterPhone(String reporterPhone) {
			this.reporterPhone = reporterPhone;
		}
	 public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getReporterId() {
		return reporterId;
	}
	public void setReporterId(Long reporterId) {
		this.reporterId = reporterId;
	}
	public String getReporterMail() {
		return reporterMail;
	}
	public void setReporterMail(String reporterMail) {
		this.reporterMail = reporterMail;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public Long getReportReasonId() {
		return reportReasonId;
	}
	public void setReportReasonId(Long reportReasonId) {
		this.reportReasonId = reportReasonId;
	}
	public Long getReportReasonText() {
		return reportReasonText;
	}
	public void setReportReasonText(Long reportReasonText) {
		this.reportReasonText = reportReasonText;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	
}
