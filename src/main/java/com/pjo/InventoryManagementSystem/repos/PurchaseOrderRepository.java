package com.pjo.InventoryManagementSystem.repos;

import com.pjo.InventoryManagementSystem.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
