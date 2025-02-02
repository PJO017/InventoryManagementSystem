package com.pjo.InventoryManagementSystem.dto;

import java.util.List;

import com.pjo.InventoryManagementSystem.models.PurchaseOrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderDTO {
    PurchaseOrderStatus status;
    List<PurchaseOrderItemDTO> items;
}
