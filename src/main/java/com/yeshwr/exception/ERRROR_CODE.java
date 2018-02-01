package com.yeshwr.exception;

public enum ERRROR_CODE {

	BAD_REQUEST("400"), INVALID_INPUT("400"), INTERNAL_SERVER_ERROR("500"), NOT_FOUND("404");

	private String code;

	ERRROR_CODE(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
