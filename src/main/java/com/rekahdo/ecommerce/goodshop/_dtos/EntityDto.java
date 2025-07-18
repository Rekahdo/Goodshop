package com.rekahdo.ecommerce.goodshop._dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Relation(collectionRelation = "items")
public class EntityDto<T extends EntityDto<T>> extends RepresentationModel<T> {

	private Long id;

}