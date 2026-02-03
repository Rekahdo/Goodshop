package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.entities.ApiEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface CrossMapper<ENTITY extends ApiEntity, SECOND> {

	ENTITY toEntity(SECOND second);
	SECOND toSecond(ENTITY entity);
	List<ENTITY> toEntityList(List<SECOND> seconds);
	List<SECOND> toSecondList(List<ENTITY> entities);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(SECOND source, @MappingTarget ENTITY target);

	@AfterMapping
	default void afterMappingToEntity(@MappingTarget ENTITY target, SECOND source){};

	@AfterMapping
	default void afterMappingToSecond(@MappingTarget SECOND target, ENTITY source){};

}
