package com.pjo.InventoryManagementSystem.dto;

import com.pjo.InventoryManagementSystem.models.PurchaseOrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderItemDTO {
    private Long id;
    private Long purchaseOrderId; 
    private Long productId; 
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
