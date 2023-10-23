package com.edugrade.edufy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object value;

	public UserAlreadyExistException() {
	}

	public UserAlreadyExistException(Object value) {
		super(constructErrorMessage(value));
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	private static String constructErrorMessage(Object value) {
		return String.format("User with ID: '%s' already exists.", value);
	}

}
