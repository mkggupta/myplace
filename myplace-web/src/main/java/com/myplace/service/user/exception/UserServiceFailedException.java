/**
 * 
 */
package com.myplace.service.user.exception;

import com.myplace.framework.exception.CDBusinessException;
import com.myplace.framework.exception.util.ErrorCodesEnum;


/**
 * @author Manish
 * 
 */
public class UserServiceFailedException extends CDBusinessException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public UserServiceFailedException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 */
	public UserServiceFailedException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param e
	 */
	public UserServiceFailedException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public UserServiceFailedException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

}
