package com.pjo.InventoryManagementSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjo.InventoryManagementSystem.dto.PurchaseOrderDTO;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderItemDTO;
import com.pjo.InventoryManagementSystem.exceptions.DatabaseOperationeException;
import com.pjo.InventoryManagementSystem.exceptions.ResourceNotFoundException;
import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.models.PurchaseOrder;
import com.pjo.InventoryManagementSystem.models.PurchaseOrderItem;
import com.pjo.InventoryManagementSystem.repos.ProductRepository;
import com.pjo.InventoryManagementSystem.repos.PurchaseOrderItemRepository;
import com.pjo.InventoryManagementSystem.repos.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

    final PurchaseOrderRepository purchaseOrderRepository;
    final PurchaseOrderItemRepository purchaseOrderItemRepository;
    final ProductRepository productRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
            PurchaseOrderItemRepository purchaseOrderItemRepository, ProductRepository productRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
        this.productRepository = productRepository;
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        logger.info("Fetching all purchase orders");
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        logger.debug("Retrieved purchase orders: {}", purchaseOrders);
        return purchaseOrders;
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {
        logger.info("Fetching purchase order with id: {}", id);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Purchase Order not found with id: " + id));
        logger.info("Found purchase order with id: {}", purchaseOrder.getId());
        return purchaseOrder;
    }

    public List<PurchaseOrderItemDTO> getAllPurchaseOrderItemsByPurchaseOrderId(Long purchaseOrderId) {
        logger.info("Fetching all items for purchase order with id: {}", purchaseOrderId);
        if (!purchaseOrderRepository.existsById(purchaseOrderId)) {
            throw new ResourceNotFoundException("Purchase Order not found with id: " + purchaseOrderId);
        }
        return purchaseOrderItemRepository.findByPurchaseOrderId(purchaseOrderId)
                .stream()
                .map(item -> new PurchaseOrderItemDTO(item))
                .toList();
    }

    private PurchaseOrderItem createValidatedPurchaseOrderItem(PurchaseOrder purchaseOrder,
            PurchaseOrderItemDTO purchaseOrderItemDTO) {
        logger.info("Creating a new purchase order item for purchase order id: {}",
                purchaseOrder.getId());

        try {
            Product product = productRepository.findById(purchaseOrderItemDTO.getProductId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "Product not found with id: " + purchaseOrderItemDTO.getProductId()));

            if (purchaseOrderItemDTO.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            return purchaseOrderItemRepository
                    .save(new PurchaseOrderItem(purchaseOrder, product, purchaseOrderItemDTO.getQuantity()));
        } catch (Exception e) {
            logger.error("Error creating purchase order item: {}", e.getMessage());
            throw new DatabaseOperationeException(
                    "Error creating purchase order item: " + e.getMessage());
        }
    }

    @Transactional
    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        logger.info("Creating a new purchase order");

        if (purchaseOrderDTO.getItems() == null || purchaseOrderDTO.getItems().isEmpty()) {
            throw new IllegalArgumentException("At least one item must be added to the purchase order");
        }

        PurchaseOrder purchaseOrder = purchaseOrderRepository.save(new PurchaseOrder());

        logger.debug("Creating purchase order items for purchase order id: {}", purchaseOrder.getId());

        List<PurchaseOrderItem> items = purchaseOrderDTO.getItems().stream().map(itemDTO -> {
            return createValidatedPurchaseOrderItem(purchaseOrder, itemDTO);
        }).collect(Collectors.toList());

        purchaseOrderItemRepository.saveAll(items);

        Double totalAmount = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice().doubleValue() * item.getQuantity())
                .sum();

        purchaseOrder.setTotalAmount(totalAmount);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Transactional
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrderDTO purchaseOrderDTO) {
        logger.info("Updating purchase order with id: {}", id);

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Purchase Order not found with id: " + id));

        purchaseOrderItemRepository.deleteByPurchaseOrderId(purchaseOrder.getId());

        List<PurchaseOrderItem> updatedItems = purchaseOrderDTO.getItems().stream()
                .map(itemDTO -> createValidatedPurchaseOrderItem(purchaseOrder, itemDTO))
                .collect(Collectors.toList());

        purchaseOrderItemRepository.saveAll(updatedItems);

        double totalAmount = updatedItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice().doubleValue() * item.getQuantity())
                .sum();
        purchaseOrder.setTotalAmount(totalAmount);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Transactional
    public void deletePurchaseOrder(Long id) {
        logger.info("Deleting purchase order with id: {}", id);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Purchase Order not found with id: " + id));
        purchaseOrderItemRepository.deleteByPurchaseOrderId(id);
        purchaseOrderRepository.delete(purchaseOrder);
    }
}