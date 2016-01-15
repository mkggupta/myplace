package com.myplace.service.notification.exception;

import com.myplace.framework.exception.CDBusinessException;
import com.myplace.framework.exception.util.ErrorCodesEnum;

public class NotificationServiceException extends CDBusinessException{
	public NotificationServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		
	}

	
	public NotificationServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		
	}

	public NotificationServiceException(String errorCode) {
		super(errorCode);
		
	}
	public NotificationServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
