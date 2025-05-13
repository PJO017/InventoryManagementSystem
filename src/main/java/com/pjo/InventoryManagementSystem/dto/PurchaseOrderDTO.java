package com.pjo.InventoryManagementSystem.dto;

import java.util.List;

import com.pjo.InventoryManagementSystem.models.PurchaseOrderStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    PurchaseOrderStatus status;
    /**
     * List of purchase order items.
     */
    @NotNull
    @NotEmpty
    @Valid
    List<PurchaseOrderItemDTO> items;
}
