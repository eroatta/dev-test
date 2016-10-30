package com.eroatta.dev.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an error on the input parameters or a malformed request.
 * 
 * @author eroatta
 *
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status = 400;
    private final String error = "Bad Request";
    private final String message;

    /**
     * Constructs the bad request exception and sets the given message.
     * 
     * @param message A message explaining the error condition.
     */
    public BadRequestException(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Gets a map error attributes, based on the exception information.
     * 
     * @return a map of error attributes.
     */
    public Map<String, Object> getErrorDescription() {
        Map<String, Object> errorDescription = new HashMap<String, Object>();
        errorDescription.put("error", "Bad Request");
        errorDescription.put("message", this.message);
        errorDescription.put("trace", null);

        return errorDescription;
    }
}
