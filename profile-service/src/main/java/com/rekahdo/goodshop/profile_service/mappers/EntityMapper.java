package com.rekahdo.goodshop.profile_service.mappers;

import com.rekahdo.goodshop.profile_service.dtos.clients.ApiClient;
import com.rekahdo.goodshop.profile_service.dtos.entities.ApiDto;
import com.rekahdo.goodshop.profile_service.dtos.request.ApiRequest;
import com.rekahdo.goodshop.profile_service.entities.ApiEntity;
import org.mapstruct.*;

import java.util.List;

public interface EntityMapper<
		ENTITY extends ApiEntity,
		REQUEST extends ApiRequest,
		DTO extends ApiDto,
		CLIENT extends ApiClient> {

	ENTITY toEntity(REQUEST request);
	DTO toDto(ENTITY entity);
	CLIENT toClient(ENTITY entity);
	List<DTO> toDtoList(List<ENTITY> entities);
	List<ENTITY> toEntityList(List<REQUEST> requests);
	List<CLIENT> toClientList(List<ENTITY> entities);

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

	@BeforeMapping
	default void beforeMappingToClient(@MappingTarget CLIENT target, ENTITY source){};

	@AfterMapping
	default void afterMappingToClient(@MappingTarget CLIENT target, ENTITY source){};

}
