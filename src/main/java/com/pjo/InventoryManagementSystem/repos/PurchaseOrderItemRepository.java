package com.pjo.InventoryManagementSystem.repos;

import com.pjo.InventoryManagementSystem.models.PurchaseOrderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {
    public List<PurchaseOrderItem> findByPurchaseOrderId(Long purchaseOrderId);
    public PurchaseOrderItem findByProductId(Long productId);
    public void deleteByPurchaseOrderId(Long purchaseOrderId);
}
