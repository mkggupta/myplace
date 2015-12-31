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
public class PushServiceFailedException extends CDBusinessException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public PushServiceFailedException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 */
	public PushServiceFailedException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param e
	 */
	public PushServiceFailedException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public PushServiceFailedException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

}
