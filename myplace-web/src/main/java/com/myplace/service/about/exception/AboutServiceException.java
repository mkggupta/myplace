package com.myplace.service.about.exception;

import com.myplace.framework.exception.CDBusinessException;
import com.myplace.framework.exception.util.ErrorCodesEnum;

public class AboutServiceException extends CDBusinessException{
	public AboutServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		
	}

	
	public AboutServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		
	}

	public AboutServiceException(String errorCode) {
		super(errorCode);
		
	}
	public AboutServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
