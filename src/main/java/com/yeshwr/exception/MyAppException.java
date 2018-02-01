package com.yeshwr.exception;

/**
 * 
 * View Model for Exception
 * 
 * @author yeshwr
 *
 */
public class MyAppException {

	private String code;
	private String message;
	
	public MyAppException(String code, String message) {
		this.code= code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
