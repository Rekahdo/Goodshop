package com.rekahdo.ecommerce.goodshop._dtos.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.rekahdo.ecommerce.goodshop._entities.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonFilter("categoryDtoFilter")
public class CategoryDto extends EntityDto<CategoryDto> {

    @NotNull(message = "Specify a name for the category")
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}