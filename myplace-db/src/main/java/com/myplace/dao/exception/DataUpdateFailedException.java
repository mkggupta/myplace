/**
 * 
 */
package com.myplace.dao.exception;

import com.myplace.framework.exception.CDSystemException;
import com.myplace.framework.exception.util.ErrorCodesEnum;

/**
 * @author Manish
 * 
 */
public class DataUpdateFailedException extends CDSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3848211064707051501L;

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public DataUpdateFailedException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 */
	public DataUpdateFailedException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param e
	 */
	public DataUpdateFailedException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public DataUpdateFailedException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

}
