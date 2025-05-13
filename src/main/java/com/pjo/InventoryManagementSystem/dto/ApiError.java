package com.pjo.InventoryManagementSystem.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO representing an API error response.
 */
@Getter
@Setter
public class ApiError {
    /**
     * HTTP status code.
     */
    private int status;
    /**
     * Error type or summary.
     */
    private String error;
    /**
     * Detailed error message.
     */
    private String message;
    /**
     * Request path where the error occurred.
     */
    private String path;
    /**
     * Time when the error occurred.
     */
    private LocalDateTime timestamp;

    public ApiError() {
    }

    public ApiError(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
