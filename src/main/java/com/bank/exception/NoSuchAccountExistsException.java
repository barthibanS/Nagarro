package com.bank.exception;

public class NoSuchAccountExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchAccountExistsException() {
	}

	public NoSuchAccountExistsException(String msg) {
		super(msg);
	}
}
