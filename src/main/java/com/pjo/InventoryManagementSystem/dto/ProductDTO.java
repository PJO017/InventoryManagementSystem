package com.pjo.InventoryManagementSystem.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for transferring product data.
 */
@Getter
@Setter
public class ProductDTO {
    /**
     * Name of the product.
     */
    private String name;
    /**
     * Description of the product.
     */
    private String description;
    /**
     * Price per unit.
     */
    private Double price;
    /**
     * Number of units in stock.
     */
    private Integer stockQuantity;
}
