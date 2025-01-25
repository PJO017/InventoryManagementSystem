package com.pjo.InventoryManagementSystem.models;

public enum PurchaseOrderStatus {
    
    DRAFT("Draft"),
    SUBMITTED("Submitted"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled"),
    PARTIALLY_FULFILLED("Partially Fulfilled"),
    FULFILLED("Fulfilled"),
    CLOSED("Closed"),
    ON_HOLD("On Hold"),
    UNDER_REVIEW("Under Review/Validation"),
    BACKORDERED("Backordered"),
    INVOICED("Invoiced"),
    PAYMENT_INITIATED("Payment Initiated"),
    PAID("Paid"),
    DISPUTED("Disputed/Contested"),
    RETURNED("Returned");

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
