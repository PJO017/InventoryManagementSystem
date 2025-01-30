package com.pjo.InventoryManagementSystem.models;

public enum PurchaseOrderStatus {
    
    DRAFT("Draft"),
    SUBMITTED("Submitted"),
    UNDER_REVIEW("Under Review/Validation"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
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