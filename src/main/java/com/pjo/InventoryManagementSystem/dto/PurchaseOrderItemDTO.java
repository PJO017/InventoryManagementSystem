package com.pjo.InventoryManagementSystem.dto;

import com.pjo.InventoryManagementSystem.models.PurchaseOrderItem;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

/**
 * DTO for transferring purchase order item data.
 */
@Getter
@Setter
public class PurchaseOrderItemDTO {
    /**
     * Item identifier.
     */
    private Long id;
    /**
     * ID of the parent purchase order.
     */
    @NotNull
    private Long purchaseOrderId;
    /**
     * ID of the product.
     */
    @NotNull
    private Long productId;
    /**
     * Quantity ordered.
     */
    @NotNull
    @Min(1)
    private int quantity;

    public PurchaseOrderItemDTO() {
    }

    public PurchaseOrderItemDTO(PurchaseOrderItem poi) {
        this.id = poi.getId();
        this.purchaseOrderId = poi.getPurchaseOrder().getId();
        this.productId = poi.getProduct().getId();
        this.quantity = poi.getQuantity();
    }
}
