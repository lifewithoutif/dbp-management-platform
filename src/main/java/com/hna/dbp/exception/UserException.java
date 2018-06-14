package com.hna.dbp.exception;

/**
 * 用户自定义的异常信息
 * 
 * @author jlgan
 *
 */
public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMsg;
	
	public static final String MAINTAINING = "系统正在维护中，请联系管理员";

	public UserException(String msg) {
		super();
		this.exceptionMsg = msg;
	}
	
	public UserException(String msg, String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		this.exceptionMsg = msg;
	}

	public UserException(String msg, String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.exceptionMsg = msg;
	}

	public UserException(String msg, String arg0) {
		super(arg0);
		this.exceptionMsg = msg;
	}

	public UserException(String msg, Throwable arg0) {
		super(arg0);
		this.exceptionMsg = msg;
	}

	public String getMsg() {
		return exceptionMsg;
	}

}
