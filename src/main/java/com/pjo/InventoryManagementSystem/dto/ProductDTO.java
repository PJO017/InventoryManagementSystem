package com.pjo.InventoryManagementSystem.dto;

import lombok.Getter;

@Getter
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
