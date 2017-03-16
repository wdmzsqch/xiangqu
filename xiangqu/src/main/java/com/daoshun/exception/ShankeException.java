/**
 * 
 */
package com.daoshun.exception;

/**
 * @author wangcl
 * 
 */
public class ShankeException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShankeException() {
		super("{\"message\":\"不在时间范围内，请稍后再试！\",\"code\":202}");
	}

}
