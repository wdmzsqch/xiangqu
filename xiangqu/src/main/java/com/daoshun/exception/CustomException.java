/**
 * 
 */
package com.daoshun.exception;


/**
 * 自定义异常基类
 * @author wangcl
 *
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -701887515801072080L;
	
	public CustomException(String msg) {
		super(msg);
	}
}
