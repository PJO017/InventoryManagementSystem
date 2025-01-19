package com.pjo.InventoryManagementSystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.repos.ProductRepository;
import com.pjo.InventoryManagementSystem.exceptions.ResourceNotFoundException;
import com.pjo.InventoryManagementSystem.dto.ProductDTO;

@SpringBootTest
public class ProductServiceImplTests {
    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("Should return all products")
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setName("Product 1");
        Product product2 = new Product();
        product2.setName("Product 2");

        List<Product> mockProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals(mockProducts, result);
        verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Should return an empty list if no products exist")
    void testGetAllProductsEmptyList() {
        Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> result = productService.getAllProducts();

        assertTrue(result.isEmpty());
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("Should return product by ID if it exists")
    public void testGetProductById_ProductExists() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);

        assertEquals(productId, result.getId());
        assertEquals("Test Product", result.getName());
    }

    @Test
    @DisplayName("Should throw exception if product by ID does not exist")
    public void testGetProductById_ProductDoesNotExist() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(productId);
        });
    }

    @Test
    @DisplayName("Should create a new product")
    public void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("New Product");
        productDTO.setDescription("New Product Description");
        productDTO.setPrice(100.0);
        productDTO.setStockQuantity(10);

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(productDTO);

        assertEquals("New Product", result.getName());
        assertEquals("New Product Description", result.getDescription());
        assertEquals(100.0, result.getPrice());
        assertEquals(10, result.getStockQuantity());
    }

    @Test
    @DisplayName("Should update an existing product")
    public void testUpdateProduct() {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Updated Product");
        productDTO.setDescription("Updated Description");
        productDTO.setPrice(150.0);
        productDTO.setStockQuantity(20);

        Product product = new Product();
        product.setId(productId);
        product.setName("Old Product");
        product.setDescription("Old Description");
        product.setPrice(100.0);
        product.setStockQuantity(10);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(productId, productDTO);

        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals("Updated Description", updatedProduct.getDescription());
        assertEquals(150.0, updatedProduct.getPrice());
        assertEquals(20, updatedProduct.getStockQuantity());
    }

    @Test
    @DisplayName("Should throw exception if product to update is not found")
    public void testUpdateProduct_ProductNotFound() {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Updated Product");
        productDTO.setDescription("Updated Description");
        productDTO.setPrice(150.0);
        productDTO.setStockQuantity(20);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.updateProduct(productId, productDTO);
        });
    }

    @Test
    @DisplayName("Should delete an existing product")
    public void testDeleteProduct_ProductExists() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(true);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    @DisplayName("Should throw exception if product to delete is not found")
    public void testDeleteProduct_ProductDoesNotExist() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.deleteProduct(productId);
        });
    }
}
