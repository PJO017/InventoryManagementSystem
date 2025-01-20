package com.pjo.InventoryManagementSystem.services;

import com.pjo.InventoryManagementSystem.dto.ProductDTO;
import com.pjo.InventoryManagementSystem.exceptions.ResourceNotFoundException;
import com.pjo.InventoryManagementSystem.models.Product;
import com.pjo.InventoryManagementSystem.repos.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        logger.debug("Retrieved products: {}", products);
        return products;
    }

    public Product getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(ProductDTO productDTO) {
        logger.info("Creating a new product");
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        logger.debug("Product created: {}", product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        logger.info("Updating product with id: {}", id);
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id));

        if (productDTO.getName() != null) {
            logger.debug("Updating product name to: {}", productDTO.getName());
            product.setName(productDTO.getName());
        }

        if (productDTO.getDescription() != null) {
            logger.debug("Updating product description to: {}", productDTO.getDescription());
            product.setDescription(productDTO.getDescription());
        }

        if (productDTO.getPrice() != null) {
            logger.debug("Updating product price to: {}", productDTO.getPrice());
            product.setPrice(productDTO.getPrice());
        }

        if (productDTO.getStockQuantity() != null) {
            logger.debug("Updating product stock quantity to: {}", productDTO.getStockQuantity());
            product.setStockQuantity(productDTO.getStockQuantity());
        }

        Product updatedProduct = productRepository.save(product);

        logger.debug("Product updated: {}", updatedProduct);
        return updatedProduct;
    }

    public void deleteProduct(Long id) {
        logger.info("Deleting product with id: {}", id);
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        logger.debug("Product with id: {} deleted", id);
    }
}
