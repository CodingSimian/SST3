package com.edugrade.edufy.exceptions.handler;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edugrade.edufy.exceptions.ResourceNotFoundException;
import com.edugrade.edufy.exceptions.UserAlreadyExistException;

@ControllerAdvice
public class GlobalExceptionHandler {

	public GlobalExceptionHandler() {
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapErrors(e));
	}
	
	private Object mapErrors(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors()
				.forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
		return errors;
	}
	
}
