/**
 * 
 */
package com.myplace.framework.exception;

import com.myplace.framework.exception.util.ErrorCodesEnum;


/**
 * @author Admin
 * 
 */
public class CDException extends Exception implements ICDException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6338260215369181196L;

	private String exceptionType;
	private String errorCode;

	/**
	 * @param errorCode
	 * @param e
	 */
	CDException(String errorCode, Exception e) {
		super(errorCode, e);
		this.errorCode = errorCode;
	}

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	CDException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum.getErrorCode(), e);
		this.errorCode = errorCodeEnum.getErrorCode();
	}

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	CDException(ErrorCodesEnum errorCodeEnum) {
		this.errorCode = errorCodeEnum.getErrorCode();
	}

	/**
	 * @param errorCode
	 */
	CDException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public CDException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CDException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rocketalk.apis.framework.exception.IRTAPIException#getExceptionType()
	 */
	public String getExceptionType() {
		return exceptionType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rocketalk.apis.framework.exception.IRTAPIException#setExceptionType (java.lang.String)
	 */
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rocketalk.apis.framework.exception.IRTAPIException#getErrorCode()
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rocketalk.apis.framework.exception.IRTAPIException#setErrorCode(java .lang.String)
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
