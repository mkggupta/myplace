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
public class DataAccessFailedException extends CDSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3848211064707051501L;

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public DataAccessFailedException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 */
	public DataAccessFailedException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param e
	 */
	public DataAccessFailedException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public DataAccessFailedException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

}
