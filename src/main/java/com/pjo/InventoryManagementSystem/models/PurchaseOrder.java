package com.pjo.InventoryManagementSystem.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a purchase order in the system.
 */
@Entity
@Getter
@Setter
public class PurchaseOrder {
    /**
     * Unique identifier for the purchase order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Status of the purchase order (see PurchaseOrderStatus).
     */
    @NotNull
    @Column(nullable = false, length = 50)
    private PurchaseOrderStatus status;

    /**
     * Total amount for the order.
     */
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Double totalAmount;

    /**
     * Date/time when the order was placed.
     */
    @NotNull
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    /**
     * Timestamp when the order was created.
     */
    @NotNull
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the order was last updated.
     */
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = PurchaseOrderStatus.DRAFT;
        totalAmount = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
