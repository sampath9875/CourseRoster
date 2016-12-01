/**
 * 
 */
package com.mindtree.testingacademy.courseroster.exceptions;

/**
 * @author azureUser
 *
 */
public class InvalidLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4396335070310289395L;

	/**
	 * 
	 */
	public InvalidLoginException() {
	}

	/**
	 * @param message
	 */
	public InvalidLoginException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidLoginException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidLoginException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
