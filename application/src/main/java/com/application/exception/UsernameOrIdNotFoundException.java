package com.application.exception;

public class UsernameOrIdNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameOrIdNotFoundException() {
		super();
	}

	public UsernameOrIdNotFoundException(String message) {
		super(message);
	}

}
