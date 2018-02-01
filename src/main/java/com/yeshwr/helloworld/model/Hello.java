package com.yeshwr.helloworld.model;

/**
 * @author yeshwr
 *
 */
public class Hello {

	private final int id;
	private final String content;
	
	public Hello(int id, String content) {
		this.id= id;
		this.content= content;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
