package org.clusus.bloomberg.exception;

import java.util.List;

public class ErrorResponse {

	private boolean status;

	private String message;

	private List<String> errors;

	public ErrorResponse(boolean status, String message) {
		this(status, message, null);
	}

	public ErrorResponse(boolean status, String message, List<String> error) {
		this.status = status;
		this.message = message;
		this.errors = error;
	}

}
