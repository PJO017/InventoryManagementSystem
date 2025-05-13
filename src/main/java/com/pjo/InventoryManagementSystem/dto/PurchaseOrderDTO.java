package com.pjo.InventoryManagementSystem.dto;

import java.util.List;

import com.pjo.InventoryManagementSystem.models.PurchaseOrderStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for transferring purchase order data.
 */
@Getter
@Setter
public class PurchaseOrderDTO {
    /**
     * Status of the purchase order.
     */
    PurchaseOrderStatus status;
    /**
     * List of purchase order items.
     */
    List<PurchaseOrderItemDTO> items;
}
