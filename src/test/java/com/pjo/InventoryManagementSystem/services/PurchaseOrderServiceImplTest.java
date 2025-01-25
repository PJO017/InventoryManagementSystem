package com.pjo.InventoryManagementSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderDTO;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderItemDTO;
import com.pjo.InventoryManagementSystem.exceptions.ResourceNotFoundException;
import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.models.PurchaseOrder;
import com.pjo.InventoryManagementSystem.models.PurchaseOrderItem;
import com.pjo.InventoryManagementSystem.repos.ProductRepository;
import com.pjo.InventoryManagementSystem.repos.PurchaseOrderItemRepository;
import com.pjo.InventoryManagementSystem.repos.PurchaseOrderRepository;

class PurchaseOrderServiceImplTest {

    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PurchaseOrderServiceImpl purchaseOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPurchaseOrders() {
        PurchaseOrder order1 = new PurchaseOrder();
        PurchaseOrder order2 = new PurchaseOrder();
        when(purchaseOrderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<PurchaseOrder> result = purchaseOrderService.getAllPurchaseOrders();

        assertEquals(2, result.size());
        verify(purchaseOrderRepository, times(1)).findAll();
    }

    @Test
    void testGetPurchaseOrderById() {
        PurchaseOrder order = new PurchaseOrder();
        order.setId(1L);
        when(purchaseOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        PurchaseOrder result = purchaseOrderService.getPurchaseOrderById(1L);

        assertEquals(1L, result.getId());
        verify(purchaseOrderRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPurchaseOrderById_NotFound() {
        when(purchaseOrderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            purchaseOrderService.getPurchaseOrderById(1L);
        });
    }

    @Test
    void testCreatePurchaseOrder() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        PurchaseOrderItemDTO itemDTO = new PurchaseOrderItemDTO();
        itemDTO.setProductId(1L);
        itemDTO.setQuantity(2);
        purchaseOrderDTO.setItems(Arrays.asList(itemDTO));

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(123L);

        Product product = new Product();
        product.setId(1L);
        product.setPrice(100.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(purchaseOrderRepository.save(any(PurchaseOrder.class))).thenReturn(purchaseOrder);
        when(purchaseOrderItemRepository.save(any(PurchaseOrderItem.class)))
                .thenReturn(new PurchaseOrderItem(purchaseOrder, product, itemDTO.getQuantity()));

        PurchaseOrder result = purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);

        assertNotNull(result);
        verify(purchaseOrderRepository, times(2)).save(any(PurchaseOrder.class));
        verify(purchaseOrderItemRepository, times(1)).save(any(PurchaseOrderItem.class));
    }

    @Test
    void testCreatePurchaseOrder_NoItems() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setItems(Collections.emptyList());

        assertThrows(IllegalArgumentException.class, () -> {
            purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
        });
    }

    @Test
    void testUpdatePurchaseOrder() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        PurchaseOrderItemDTO itemDTO = new PurchaseOrderItemDTO();
        itemDTO.setProductId(1L);
        itemDTO.setQuantity(2);
        purchaseOrderDTO.setItems(Arrays.asList(itemDTO));

        PurchaseOrder existingOrder = new PurchaseOrder();
        existingOrder.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setPrice(100.0);

        when(purchaseOrderRepository.findById(1L)).thenReturn(Optional.of(existingOrder));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(purchaseOrderRepository.save(any(PurchaseOrder.class))).thenReturn(existingOrder);
        when(purchaseOrderItemRepository.save(any(PurchaseOrderItem.class)))
                .thenReturn(new PurchaseOrderItem(existingOrder, product, itemDTO.getQuantity()));

        PurchaseOrder result = purchaseOrderService.updatePurchaseOrder(1L, purchaseOrderDTO);

        assertNotNull(result);
        verify(purchaseOrderRepository, times(1)).findById(1L);
        verify(purchaseOrderRepository, times(1)).save(any(PurchaseOrder.class));
        verify(purchaseOrderItemRepository, times(1)).deleteByPurchaseOrderId(1L);
        verify(purchaseOrderItemRepository, times(1)).save(any(PurchaseOrderItem.class));
    }

    @Test
    void testUpdatePurchaseOrder_NonExistentOrder() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        PurchaseOrderItemDTO itemDTO = new PurchaseOrderItemDTO();
        itemDTO.setProductId(1L);
        itemDTO.setQuantity(2);
        purchaseOrderDTO.setItems(Arrays.asList(itemDTO));

        when(purchaseOrderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            purchaseOrderService.updatePurchaseOrder(1L, purchaseOrderDTO);
        });
    }

    @Test
    void testDeletePurchaseOrder() {
        PurchaseOrder order = new PurchaseOrder();
        order.setId(1L);
        when(purchaseOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        purchaseOrderService.deletePurchaseOrder(1L);

        verify(purchaseOrderRepository, times(1)).findById(1L);
        verify(purchaseOrderRepository, times(1)).delete(order);
        verify(purchaseOrderItemRepository, times(1)).deleteByPurchaseOrderId(1L);
    }

}