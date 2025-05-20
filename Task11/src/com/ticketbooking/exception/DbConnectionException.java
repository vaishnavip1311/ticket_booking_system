package com.ticketbooking.exception;

import com.ticketbooking.util.HexaConstants;

public class DbConnectionException extends Exception{

	public DbConnectionException() {
		super();
	}

	public DbConnectionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DbConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbConnectionException(String message) {
		super(message);
	}

	public DbConnectionException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return HexaConstants.CANNOT_OPEN_CONNECTION;
	}
	
	

}
