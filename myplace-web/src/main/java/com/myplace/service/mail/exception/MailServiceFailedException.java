package com.myplace.service.mail.exception;

import com.myplace.framework.exception.CDBusinessException;
import com.myplace.framework.exception.util.ErrorCodesEnum;

public class MailServiceFailedException extends CDBusinessException{
	public MailServiceFailedException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		
	}

	
	public MailServiceFailedException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		
	}

	public MailServiceFailedException(String errorCode) {
		super(errorCode);
		
	}
	public MailServiceFailedException(String errorCode, Exception e) {
		super(errorCode, e);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
