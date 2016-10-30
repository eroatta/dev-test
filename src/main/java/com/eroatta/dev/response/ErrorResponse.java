package com.eroatta.dev.response;

import java.util.Map;

/**
 * Defines the structure for the output of an error.
 * 
 * @author eroatta
 *
 */
public class ErrorResponse {

    private final int status;
    private final String error;
    private final String message;
    private final String cause;

    /**
     * Constructs the error response based on the status and the error
     * description.
     * 
     * @param status
     *            The HTTP Status for the error.
     * @param errorDescription
     *            A map of error attributes.
     */
    public ErrorResponse(int status, Map<String, Object> errorDescription) {
        this.status = status;
        this.error = (String) errorDescription.get("error");
        this.message = (String) errorDescription.get("message");
        this.cause = (String) errorDescription.get("trace");
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

    public String getCause() {
        return cause;
    }
}
