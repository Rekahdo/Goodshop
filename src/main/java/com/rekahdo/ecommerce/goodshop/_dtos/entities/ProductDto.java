package com.rekahdo.ecommerce.goodshop._dtos.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@JsonFilter("productDtoFilter")
public class ProductDto extends EntityDto<ProductDto> {

    @NotNull(message = "Specify product name")
    private String name;

    @NotNull(message = "Specify product description")
    private String description;

    @NotNull(message = "Specify product price")
    private String price;

    @NotNull(message = "Specify product category id")
    private Long categoryId;

    private String category;

    public ProductDto(String name, String description, String price, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }
}