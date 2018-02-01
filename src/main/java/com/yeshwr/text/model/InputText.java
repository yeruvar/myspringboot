package com.yeshwr.text.model;

import javax.validation.constraints.NotNull;

/**
 * @author yeshwr
 *
 */
public class InputText {

	@NotNull
	private Integer id;

	@NotNull
	private String text;

	/**
	 * Constructs an instance of this class.
	 * 
	 * @param id
	 * @param text
	 */
	public InputText(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	/**
	 * Constructs an instance of this class
	 */
	public InputText() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
