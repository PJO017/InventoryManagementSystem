package com.pjo.InventoryManagementSystem.repos;

import com.pjo.InventoryManagementSystem.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
