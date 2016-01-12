package com.myplace.service.media.exception;

import com.myplace.framework.exception.CDBusinessException;
import com.myplace.framework.exception.util.ErrorCodesEnum;

public class MediaServiceException extends CDBusinessException{
	public MediaServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		
	}

	
	public MediaServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		
	}

	public MediaServiceException(String errorCode) {
		super(errorCode);
		
	}
	public MediaServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
