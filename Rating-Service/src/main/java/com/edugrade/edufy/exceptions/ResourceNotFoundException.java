package com.edugrade.edufy.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends NoSuchElementException {

	private static final long serialVersionUID = 1L;
	private String name, field;
	private Object value;

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(String name, String field, Object value) {
		super(String.format("%s with %s : '%s' was not found", name, field, value));
		this.field = field;
		this.name = name;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}
