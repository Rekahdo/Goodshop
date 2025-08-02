package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._dtos.entities.EntityDto;
import org.mapstruct.*;

import java.util.List;

public interface Api_Mapper<ENTITY, DTO extends EntityDto<DTO>> {

	DTO toDto(ENTITY entity);
	ENTITY toEntity(DTO dto);
	List<DTO> toDtoList(List<ENTITY> entities);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(DTO source, @MappingTarget ENTITY target);

	@BeforeMapping
	default void beforeMappingToDto(@MappingTarget DTO target, ENTITY source){};

	@BeforeMapping
	default void beforeMappingToEntity(@MappingTarget ENTITY target, DTO source){};

	@AfterMapping
	default void afterMappingToDto(@MappingTarget DTO target, ENTITY source){};

	@AfterMapping
	default void afterMappingToEntity(@MappingTarget ENTITY target, DTO source){};

}
