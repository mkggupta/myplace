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
public class DeviceRegFailedException extends CDBusinessException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public DeviceRegFailedException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 */
	public DeviceRegFailedException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param e
	 */
	public DeviceRegFailedException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public DeviceRegFailedException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

}
