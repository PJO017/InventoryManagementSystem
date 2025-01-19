package com.pjo.InventoryManagementSystem.controllers;

import com.pjo.InventoryManagementSystem.dto.ProductDTO;
import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product1;
    private Product product1Updated;
    private Product product2;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Test Product 1");

        product1Updated = new Product();
        product1Updated.setId(1L);
        product1Updated.setName("Updated Product");

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Test Product 2");

        products = Arrays.asList(product1, product2);
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(products);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Test Product 1"))
                .andExpect(jsonPath("$.data[1].name").value("Test Product 2"));
        verify(productService).getAllProducts();
    }

    @Test
    void testGetProduct() throws Exception {
        when(productService.getProductById(1L)).thenReturn(product1);
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Test Product 1"));
        verify(productService).getProductById(1L);
    }

    @Test
    void testSaveProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Test Product");
        newProduct.setDescription("Description");
        newProduct.setPrice(100.0);
        newProduct.setStockQuantity(10);

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(newProduct);
        String payload = "{\n" +
                "    \"name\": \"Test Product\",\n" +
                "    \"description\": \"Description\",\n" +
                "    \"price\": 100.0,\n" +
                "    \"stockQuantity\": 10\n" +
                "}";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("Test Product"))
                .andExpect(jsonPath("$.data.description").value("Description"))
                .andExpect(jsonPath("$.data.price").value(100))
                .andExpect(jsonPath("$.data.stockQuantity").value(10));
        verify(productService).createProduct(any(ProductDTO.class));
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(product1Updated);
        String payload = "{\"name\":\"Updated Product\"}";
        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Updated Product"));
        verify(productService).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product deleted successfully"));
        verify(productService).deleteProduct(1L);
    }
}