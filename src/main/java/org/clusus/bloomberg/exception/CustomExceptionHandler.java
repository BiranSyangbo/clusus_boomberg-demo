package org.clusus.bloomberg.exception;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, TypeMismatchException.class })
	public ResponseEntity<Object> handleException(TypeMismatchException e, HttpHeaders headers, HttpStatus status,
			WebRequest webRequest) {
		ErrorResponse responseMessage = new ErrorResponse(false, e.getLocalizedMessage());
		return hanleException(e, responseMessage, headers, status, webRequest);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ErrorResponse apiError = new ErrorResponse(false, ex.getLocalizedMessage(), errors);
		return hanleException(ex, apiError, headers, status, request);
	}

	protected ResponseEntity<Object> hanleException(Exception ex, @Nullable ErrorResponse body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}

}
