package com.retailer.rewards.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> genericExceptions(Exception ex, WebRequest request) {
		return buildErrorResponse("Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgValidationException(MethodArgumentNotValidException ex, WebRequest request) {
		String errors = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.joining("; "));

		return buildErrorResponse("Validation failed: " + errors, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
		String message = String.format("Invalid type for parameter '%s'. Expected '%s'.", ex.getName(),
				ex.getRequiredType().getSimpleName());
		return buildErrorResponse(message, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * This method generates Error Response using the parameters provided
	 * 
	 * @param message
	 * @param status
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("error", status.getReasonPhrase());
		body.put("message", message);
		body.put("path", request.getDescription(false).replace("uri=", ""));

		return new ResponseEntity<>(body, new HttpHeaders(), status);
	}

}
