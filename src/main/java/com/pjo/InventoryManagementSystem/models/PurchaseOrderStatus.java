package com.pjo.InventoryManagementSystem.models;

/**
 * Enum representing the status of a purchase order.
 */
public enum PurchaseOrderStatus {
    /** Draft state. */
    DRAFT("Draft"),
    /** Submitted for review. */
    SUBMITTED("Submitted"),
    /** Under review/validation. */
    UNDER_REVIEW("Under Review/Validation"),
    /** Approved order. */
    APPROVED("Approved"),
    /** Rejected order. */
    REJECTED("Rejected"),
    /** Canceled order. */
    CANCELED("Canceled");

    private final String description;

    PurchaseOrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name() + " (" + description + ")";
    }
}