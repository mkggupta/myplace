package com.myplace.service.about.exception;

import com.myplace.framework.exception.CDBusinessException;
import com.myplace.framework.exception.util.ErrorCodesEnum;

public class AboutServiceException extends CDBusinessException{
	public AboutServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	
	public AboutServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	public AboutServiceException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public AboutServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
