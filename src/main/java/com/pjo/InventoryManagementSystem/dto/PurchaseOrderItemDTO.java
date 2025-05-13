package com.pjo.InventoryManagementSystem.dto;

import com.pjo.InventoryManagementSystem.models.PurchaseOrderItem;

import lombok.Getter;
import lombok.Setter;

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
    private Long purchaseOrderId;
    /**
     * ID of the product.
     */
    private Long productId;
    /**
     * Quantity ordered.
     */
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
