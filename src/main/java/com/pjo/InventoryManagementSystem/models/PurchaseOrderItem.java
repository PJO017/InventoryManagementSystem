package com.pjo.InventoryManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents an item within a purchase order.
 */
@Entity
@Getter
@Setter
public class PurchaseOrderItem {
    /**
     * Unique identifier for the item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to the parent purchase order.
     */
    @ManyToOne
    private PurchaseOrder purchaseOrder;

    /**
     * Reference to the product being ordered.
     */
    @ManyToOne
    private Product product;

    /**
     * Quantity of the product ordered.
     */
    @Column(nullable = false)
    private int quantity;

    /**
     * Timestamp when the item was created.
     */
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the item was last updated.
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

    public PurchaseOrderItem() {
    }

    public PurchaseOrderItem(PurchaseOrder purchaseOrder, Product product, int quantity) {
        this.purchaseOrder = purchaseOrder;
        this.product = product;
        this.quantity = quantity;
    }
}