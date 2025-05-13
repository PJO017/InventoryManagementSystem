package com.pjo.InventoryManagementSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    /**
     * Description of the product.
     */
    @Size(max = 255)
    private String description;

    /**
     * Price per unit.
     */
    @NotNull
    @Positive
    private Double price;

    /**
     * Number of units in stock.
     */
    @NotNull
    @Min(0)
    private Integer stockQuantity;
}
