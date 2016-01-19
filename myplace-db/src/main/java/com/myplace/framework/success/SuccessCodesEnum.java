/**
 * 
 */
package com.myplace.framework.success;

/**
 * @author manish
 * 
 */
public enum SuccessCodesEnum {

	COMMON_SUCCESS("SUCC_10001", "Operation completed successfully", "common.success"),
	
	// 11101 - USER SERVICE SUCCESS MESSAGES

	// 11201 - AUTHENTICATION SERVICE SUCCESS MESSAGES

	// 11301 - ADVT SERVICE SUCCESS MESSAGES
	
	
	NO_ADVT_TEMPLATE_SUCCESS("SUCC_11301", "Currently there is no advertisement template.", "no.advt.template.success"),
	LOG_REG_SUCCESS("SUCC_11302", "Register/Login sucessfully.", "log.reg.success"),
	NO_ADVT_SUCCESS("SUCC_11303", "Currently there is no advertisement.", "no.advt.success"),
	ADVT_STATUS_CHANGE_SUCCESS("SUCC_11304", "Advertisement status has been changed successfully.", "advt.status.change.success"),
	NO_ADVT_STATUS_SUCCESS("SUCC_11305", "No action taken.", "advt.no.action.success"),
	NO_CAT_SUCCESS("SUCC_11306", "Currently there is no category.", "no.cat.success"),
	NO_SUB_CAT_SUCCESS("SUCC_11307", "Currently there is no sub category.", "no.sub.cat.success"),
	NO_BUSINESS_SUCCESS("SUCC_11308", "Currently there is no business.", "no.business.success"),
	BUSS_STATUS_CHANGE_SUCCESS("SUCC_11309", "Business status has been changed successfully.", "buss.status.change.success"),
	BUSS_CHANGE_SUCCESS("SUCC_11310", "Business details has been changed successfully.", "buss.detail.change.success"),
	NO_BUSS_STATUS_SUCCESS("SUCC_11311", "No action taken.", "buss.no.action.success"),
	NO_ABOUT_SUCCESS("SUCC_11312", "Page is under construction.", "no.about.success"),
	NO_FEEDBACK_SUCCESS("SUCC_11313", "Currently there is no feedback.Write your own experience with this application.", "no.feedback.success"),
	LOG_REG_ALREADY_EXIST("SUCC_11314", "This Email address is already registered with us.", "log.reg.success"),
	LOG_DEVICE_REG_SUCCESS("SUCC_11315", "Device for push notification registered sucessfully.", "log.device.reg.success"),
	LOG_DEVICE_REG_ALREADY_EXIST("SUCC_11316", "This Device is already registered for push notification with us.", "log.device.already.reg.success"),
	DELETE_FEEDBACK_SUCCESS("SUCC_11317", "Feedback deleted successfully.", "delete.feedback.success"),
	DELETE_REPLY_FEEDBACK_SUCCESS("SUCC_11318", "Reply feedback deleted successfully", "reply.feedback.feedback.success"),
	CHANGE_FEEDBACK_SUCCESS("SUCC_11319", "Feedback status has been changed successfully.", "change.feedback.status.success"),
	CHANGE_FEEDBACK_REPLY_SUCCESS("SUCC_11320", "Status has been changed successfully.", "change.reply.feedback.status.success"),
	NO_NOTIF_SUCCESS("SUCC_113021", "Currently there is no notification.", "no.notif.success"),
	NOTIF_DELETE_SUCCESS("SUCC_113022", "Notification is deleted successfully.", "notif.delete.success"),
	PASSWORD_CHANGE_SUCCESS("SUCC_11323", "Password changed successfully.", "log.password.change.success"),
	EMAIL_VARIFICATION_SUCCESS("SUCC_11324", "Email is verified successfully.", "log.email.veriy.success"),
	EMAIL_ALREADY_VARIFICATION_SUCCESS("SUCC_11325", "Email is verified successfully.", "log.email.veriy.success"),
	FORGET_PASSWORD_EMAIL_SUCCESS("SUCC_11326", "Email is sent to your inbox successfully.", "log.forget.email.sent.success"),
	RESET_PASSWORD_SUCCESS("SUCC_11327", "Password is changed successfully.", "log.reset.password.change.success"),
	BUSS_DELETE_SUCCESS("SUCC_11328", "Business has been deleted successfully.", "buss.delete.success"),
	;
	String successCode;
	String successMessage;
	String successLabelCode;

	SuccessCodesEnum(String successCode, String successMessage, String successLabelCode) {
		this.successCode = successCode;
		this.successMessage = successMessage;
		this.successLabelCode = successLabelCode;
	}

	/**
	 * @return the successCode
	 */
	public String getSuccessCode() {
		return successCode;
	}

	/**
	 * @param successCode
	 *            the successCode to set
	 */
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}

	/**
	 * @return the successMessage
	 */
	public String getSuccessMessage() {
		return successMessage;
	}

	/**
	 * @param successMessage
	 *            the successMessage to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	/**
	 * @return the successLabelCode
	 */
	public String getSuccessLabelCode() {
		return successLabelCode;
	}

	/**
	 * @param successLabelCode
	 *            the successLabelCode to set
	 */
	public void setSuccessLabelCode(String successLabelCode) {
		this.successLabelCode = successLabelCode;
	}

	public static SuccessCodesEnum getSuccessCodesEnum(String successCode) {
		SuccessCodesEnum[] successCodesEnumArr = SuccessCodesEnum.values();
		for (SuccessCodesEnum successCodesEnum : successCodesEnumArr) {
			if (successCodesEnum.getSuccessCode().equalsIgnoreCase(successCode)) {
				return successCodesEnum;
			}
		}

		return COMMON_SUCCESS;
	}

}
