package com.myplace.service.search.exception;

import com.myplace.framework.exception.CDBusinessException;
import com.myplace.framework.exception.util.ErrorCodesEnum;

public class SearchServiceException extends CDBusinessException{
	public SearchServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	
	public SearchServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	public SearchServiceException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public SearchServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
