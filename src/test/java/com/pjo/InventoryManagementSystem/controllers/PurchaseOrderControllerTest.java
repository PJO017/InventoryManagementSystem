package com.pjo.InventoryManagementSystem.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderDTO;
import com.pjo.InventoryManagementSystem.dto.PurchaseOrderItemDTO;
import com.pjo.InventoryManagementSystem.models.PurchaseOrder;
import com.pjo.InventoryManagementSystem.services.PurchaseOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class PurchaseOrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PurchaseOrderService purchaseOrderService;

    @InjectMocks
    private PurchaseOrderController purchaseOrderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseOrderController).build();
    }

    @Test
    public void testGetAllPurchaseOrders() throws Exception {
        List<PurchaseOrder> purchaseOrders = Collections.singletonList(new PurchaseOrder());
        when(purchaseOrderService.getAllPurchaseOrders()).thenReturn(purchaseOrders);

        mockMvc.perform(get("/api/purchase-orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.message").value("Purchase orders retrieved successfully"));
    }

    @Test
    public void testGetPurchaseOrder() throws Exception {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        when(purchaseOrderService.getPurchaseOrderById(1L)).thenReturn(purchaseOrder);

        mockMvc.perform(get("/api/purchase-orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Purchase order retrieved successfully"));
    }

    @Test
    public void testGetPurchaseOrderItems() throws Exception {
        List<PurchaseOrderItemDTO> items = Collections.singletonList(new PurchaseOrderItemDTO());
        when(purchaseOrderService.getAllPurchaseOrderItemsByPurchaseOrderId(1L)).thenReturn(items);

        mockMvc.perform(get("/api/purchase-orders/1/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.message").value("Purchase order items retrieved successfully"));
    }

    @Test
    public void testSavePurchaseOrder() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        when(purchaseOrderService.createPurchaseOrder(any(PurchaseOrderDTO.class))).thenReturn(purchaseOrder);

        mockMvc.perform(post("/api/purchase-orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"field\":\"value\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Purchase order created successfully"));
    }

    @Test
    public void testUpdatePurchaseOrder() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        PurchaseOrder updatedOrder = new PurchaseOrder();
        when(purchaseOrderService.updatePurchaseOrder(eq(1L), any(PurchaseOrderDTO.class))).thenReturn(updatedOrder);

        mockMvc.perform(put("/api/purchase-orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Purchase order updated successfully"));
    }

    @Test
    public void testDeletePurchaseOrder() throws Exception {
        doNothing().when(purchaseOrderService).deletePurchaseOrder(1L);

        mockMvc.perform(delete("/api/purchase-orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Purchase order deleted successfully"));
    }
}