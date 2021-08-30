package org.clusus.bloomberg.exception;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({EntityAlreadyExist.class})
    public ResponseEntity<Object> entityAlreadyExists(EntityAlreadyExist e) {
        log.error("EntityAlreadyExist Exception", e);
        ErrorResponse responseMessage = new ErrorResponse(false, e.getLocalizedMessage());
        return handleException(e, responseMessage, null, HttpStatus.CONFLICT, null);
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class, TypeMismatchException.class})
    public ResponseEntity<Object> handleException(TypeMismatchException e, HttpHeaders headers, HttpStatus status,
                                                  WebRequest webRequest) {
        log.error("Type Mismatch Exception", e);
        ErrorResponse responseMessage = new ErrorResponse(false, e.getLocalizedMessage());
        return handleException(e, responseMessage, headers, status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }
        log.error("Invalid Request", ex);
        ErrorResponse apiError = new ErrorResponse(false, "", errors);
        return handleException(ex, apiError, headers, status, request);
    }

    protected ResponseEntity<Object> handleException(Exception ex, @Nullable ErrorResponse body, HttpHeaders headers,
                                                     HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        String bodies = "";
        try {
            bodies = new ObjectMapper()
                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .writeValueAsString(body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bodies, headers, status);
    }

}
