package com.pjo.InventoryManagementSystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderDTO {
    List<PurchaseOrderItemDTO> items;
}
