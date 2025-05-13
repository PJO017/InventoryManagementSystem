package com.pjo.InventoryManagementSystem.controllers;

import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.dto.ApiResponse;
import com.pjo.InventoryManagementSystem.dto.ProductDTO;
import com.pjo.InventoryManagementSystem.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Retrieves all products.
	 *
	 * @return ApiResponse containing the list of all products.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<List<Product>> getAllProducts() {
		logger.info("GET request to fetch all products");
		List<Product> products = this.productService.getAllProducts();
		return new ApiResponse<>("success", products, "Products retrieved successfully");
	}

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param id the ID of the product to retrieve
	 * @return ApiResponse containing the requested product
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<Product> getProduct(@PathVariable("id") Long id) {
		logger.info("GET request to fetch product with id: {}", id);
		Product product = this.productService.getProductById(id);
		return new ApiResponse<>("success", product, "Product retrieved successfully");
	}

	/**
	 * Creates a new product.
	 *
	 * @param productDTO the product data to create
	 * @return ApiResponse containing the created product
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<Product> saveProduct(@RequestBody ProductDTO productDTO) {
		logger.info("POST request to create product with data: {}", productDTO);
		Product product = productService.createProduct(productDTO);
		return new ApiResponse<>("success", product, "Product created successfully");
	}

	/**
	 * Updates an existing product by its ID.
	 *
	 * @param id         the ID of the product to update
	 * @param productDTO the updated product data
	 * @return ApiResponse containing the updated product
	 */
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
		logger.info("PUT request to update product with id: {}, data: {}", id, productDTO);
		Product product = productService.updateProduct(id, productDTO);
		return new ApiResponse<>("success", product, "Product updated successfully");
	}

	/**
	 * Deletes a product by its ID.
	 *
	 * @param id the ID of the product to delete
	 * @return ApiResponse indicating the result of the deletion
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<?> deleteProduct(@PathVariable("id") Long id) {
		logger.info("DELETE request to delete product with id: {}", id);
		productService.deleteProduct(id);
		return new ApiResponse<>("success", null, "Product deleted successfully");
	}
}
