package com.yeshwr.fibonacci.model;

/**
 * View Model to store fibonacci value
 * 
 * @author yeshwr
 *
 */
public class Fibonacci {

	private int id;
	private long value;

	public Fibonacci(int id, long value) {
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
