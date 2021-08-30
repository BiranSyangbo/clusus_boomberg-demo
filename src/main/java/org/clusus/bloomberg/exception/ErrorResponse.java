package org.clusus.bloomberg.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ErrorResponse {

    private boolean status;

    private String message;

    private Map<String, Object> errors;

    public ErrorResponse(boolean status, String message) {
        this(status, message, Collections.emptyMap());
    }

    public ErrorResponse(boolean status, String message, Map<String, Object> error) {
        this.status = status;
        this.message = message;
        this.errors = error;
    }

}
