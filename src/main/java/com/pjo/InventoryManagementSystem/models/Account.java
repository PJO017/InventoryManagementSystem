package com.pjo.InventoryManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a user account in the system.
 */
@Entity
@Getter
@Setter
public class Account {
    /**
     * Unique identifier for the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique username for login.
     */
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    /**
     * Hashed password for authentication.
     */
    @Column(nullable = false, length = 255)
    private String password;

    /**
     * Role of the user (e.g., ADMIN, USER).
     */
    @Column(nullable = false, length = 50)
    private String role;

    /**
     * Timestamp when the account was created.
     */
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the account was last updated.
     */
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}