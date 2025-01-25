package com.pjo.InventoryManagementSystem.services;

import java.util.List;

import com.pjo.InventoryManagementSystem.dto.PurchaseOrderDTO;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderItemDTO;
import com.pjo.InventoryManagementSystem.models.PurchaseOrder;

public interface PurchaseOrderService {
    List<PurchaseOrder> getAllPurchaseOrders();

    PurchaseOrder getPurchaseOrderById(Long id);

    PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrderDTO purchaseOrderDTO);

    void deletePurchaseOrder(Long id);

    List<PurchaseOrderItemDTO> getAllPurchaseOrderItemsByPurchaseOrderId(Long purchaseOrderId);
}
