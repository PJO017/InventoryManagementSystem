package com.pjo.InventoryManagementSystem.controllers;

import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.dto.ApiResponse;
import com.pjo.InventoryManagementSystem.dto.ProductDTO;
import com.pjo.InventoryManagementSystem.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
		logger.info("GET request to fetch all products");
		List<Product> products = this.productService.getAllProducts();
		ApiResponse<List<Product>> response = new ApiResponse<>("success", products, "Products retrieved successfully");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable("id") Long id) {
		logger.info("GET request to fetch product with id: {}", id);
		Product product = this.productService.getProductById(id);
		ApiResponse<Product> response = new ApiResponse<>("success", product, "Product retrieved successfully");
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Product>> saveProduct(@RequestBody ProductDTO productDTO) {
		logger.info("POST request to create product with data: {}", productDTO);
		Product product = productService.createProduct(productDTO);
		ApiResponse<Product> response = new ApiResponse<>("success", product, "Product created successfully");
		return ResponseEntity.status(201).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
		logger.info("PUT request to update product with id: {}, data: {}", id, productDTO);
		Product product = productService.updateProduct(id, productDTO);
		ApiResponse<Product> response = new ApiResponse<>("success", product, "Product updated successfully");
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable("id") Long id) {
		logger.info("DELETE request to delete product with id: {}", id);
		productService.deleteProduct(id);
		return ResponseEntity.ok(new ApiResponse<>("success", null, "Product deleted successfully"));
	}
}
