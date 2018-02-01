package com.yeshwr.exception;

/**
 * @author yeshwr
 *
 */
public class ResourceNotFoundException extends CoreApplicationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of this class.
	 *
	 * @param exception
	 *            A reference to the initial exception.
	 */
	public ResourceNotFoundException(Exception exception) {
		super(exception);
	}

	/**
	 * Constructs an instance of this class.
	 *
	 * @param aErrorMessage
	 *            A description to the exception that occurred.
	 * 
	 * @param exception
	 *            A reference to the initial exception.
	 */
	public ResourceNotFoundException(String aErrorMessage, Exception exception) {
		super(aErrorMessage, exception);
	}
}
