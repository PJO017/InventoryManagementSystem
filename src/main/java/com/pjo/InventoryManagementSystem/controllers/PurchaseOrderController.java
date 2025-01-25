package com.pjo.InventoryManagementSystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pjo.InventoryManagementSystem.dto.ApiResponse;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderDTO;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderItemDTO;
import com.pjo.InventoryManagementSystem.models.PurchaseOrder;
import com.pjo.InventoryManagementSystem.services.PurchaseOrderService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<PurchaseOrder>> getAllPurchaseOrders() {
        logger.info("GET request to fetch all purchase orders");
        List<PurchaseOrder> purchaseOrders = this.purchaseOrderService.getAllPurchaseOrders();
        return new ApiResponse<>("success", purchaseOrders, "Purchase orders retrieved successfully");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PurchaseOrder> getPurchaseOrder(@PathVariable("id") Long id) {
        logger.info("GET request to fetch purchase order with id: {}", id);
        PurchaseOrder purchaseOrder = this.purchaseOrderService.getPurchaseOrderById(id);
        return new ApiResponse<>("success", purchaseOrder, "Purchase order retrieved successfully");
    }

    @GetMapping("/{id}/items")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<PurchaseOrderItemDTO>> getPurchaseOrderItems(@PathVariable("id") Long id) {
        logger.info("GET request to fetch items for purchase order with id: {}", id);
        List<PurchaseOrderItemDTO> items = this.purchaseOrderService.getAllPurchaseOrderItemsByPurchaseOrderId(id);
        return new ApiResponse<>("success", items, "Purchase order items retrieved successfully");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PurchaseOrder> savePurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        logger.info("POST request to create purchase order with data: {}", purchaseOrderDTO);
        PurchaseOrder purchaseOrder = this.purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
        return new ApiResponse<>("success", purchaseOrder, "Purchase order created successfully");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PurchaseOrder> updatePurchaseOrder(@PathVariable("id") String id,
            @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        logger.info("PUT request to update purchase order with id: {}, data: {}", id, purchaseOrderDTO);
        PurchaseOrder updatedOrder = this.purchaseOrderService.updatePurchaseOrder(Long.parseLong(id),
                purchaseOrderDTO);
        return new ApiResponse<>("success", updatedOrder, "Purchase order updated successfully");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deletePurchaseOrder(@PathVariable("id") Long id) {
        logger.info("DELETE request to delete purchase order with id: {}", id);
        this.purchaseOrderService.deletePurchaseOrder(id);
        return new ApiResponse<>("success", null, "Purchase order deleted successfully");
    }
}
