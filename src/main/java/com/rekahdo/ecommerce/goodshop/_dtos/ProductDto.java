package com.rekahdo.ecommerce.goodshop._dtos;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFilter("profileDtoFilter")
public class ProductDto extends EntityDto<ProductDto> {



}