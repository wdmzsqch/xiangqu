/**
 * 
 */
package com.daoshun.exception;

/**
 * @author wangcl
 * 
 */
public class NullParameterException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullParameterException() {
		super("{\"message\":\"网络状况不佳，请稍后再试！\",\"code\":99}");
	}

}
