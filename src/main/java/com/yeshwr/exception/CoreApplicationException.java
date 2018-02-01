package com.yeshwr.exception;

/**
 * Custom Exception class
 * 
 * @author yeshwr
 *
 */
public class CoreApplicationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs an instance of this class.
	 *
	 * @param aErrorMessage
	 * 			A description to the exception that occurred.
	 */
	public CoreApplicationException(String aErrorMessage) {
		super(aErrorMessage);
	}
	
	/**
	 * Constructs an instance of this class.
	 *
	 * @param exception
	 * 			A reference to the initial exception.
	 */
	public CoreApplicationException(Exception exception)
	{
		super(exception);
	}
	
	/**
	 * Constructs an instance of this class.
	 *
	 * @param aErrorMessage
	 * 			A description to the exception that occurred.
	 * 
	 * @param exception
	 * 			A reference to the initial exception.
	 */
	public CoreApplicationException(String aErrorMessage, Exception exception)
	{
		super(aErrorMessage, exception);
	}

}
