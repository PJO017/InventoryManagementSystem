package com.pjo.InventoryManagementSystem.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * Generic DTO for API responses.
 */
@Getter
@Setter
public class ApiResponse<T> {
    /**
     * Status of the response (e.g., "success", "error").
     */
    private String status;
    /**
     * Response data payload.
     */
    private T data;
    /**
     * Additional message.
     */
    private String message;
    /**
     * Time when the response was generated.
     */
    private LocalDateTime timestamp;

    public ApiResponse() {
    }

    public ApiResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
