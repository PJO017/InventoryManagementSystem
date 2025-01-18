package com.pjo.InventoryManagementSystem.controllers;

import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.dto.ApiResponse;
import com.pjo.InventoryManagementSystem.dto.ProductDTO;
import com.pjo.InventoryManagementSystem.services.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
		List<Product> products = this.productService.getAllProducts();
		ApiResponse<List<Product>> response = new ApiResponse<>("success", products, "Products retrieved successfully");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable("id") Long id) {
		Product product = this.productService.getProductById(id);
		ApiResponse<Product> response = new ApiResponse<>("success", product, "Product retrieved successfully");
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Product>> saveProduct(@RequestBody ProductDTO productDTO) {
		Product product = productService.createProduct(productDTO);
		ApiResponse<Product> response = new ApiResponse<>("success", product, "Product created successfully");
		return ResponseEntity.status(201).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
		Product product = productService.updateProduct(id, productDTO);
		ApiResponse<Product> response = new ApiResponse<>("success", product, "Product updated successfully");
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok(new ApiResponse<>("success", null, "Product deleted successfully"));
	}
}
