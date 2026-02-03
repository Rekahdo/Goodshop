package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.ApiDto;
import com.rekahdo.goodshop.vendor_service.dtos.request.ApiRequest;
import com.rekahdo.goodshop.vendor_service.entities.ApiEntity;
import org.mapstruct.*;

import java.util.List;

public interface EntityMapper<
		ENTITY extends ApiEntity,
		REQUEST extends ApiRequest,
		DTO extends ApiDto> {

	ENTITY toEntity(REQUEST request);
	DTO toDto(ENTITY entity);
	List<DTO> toDtoList(List<ENTITY> entities);
	List<ENTITY> toEntityList(List<REQUEST> requests);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(REQUEST source, @MappingTarget ENTITY target);

	@BeforeMapping
	default void beforeMappingToDto(@MappingTarget DTO target, ENTITY source){};

	@AfterMapping
	default void afterMappingToDto(@MappingTarget DTO target, ENTITY source){};

	@BeforeMapping
	default void beforeMappingToEntity(@MappingTarget ENTITY target, REQUEST source){};

	@AfterMapping
	default void afterMappingToEntity(@MappingTarget ENTITY target, REQUEST source){};

}
