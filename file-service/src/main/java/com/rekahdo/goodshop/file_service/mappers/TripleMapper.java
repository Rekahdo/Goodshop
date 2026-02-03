package com.rekahdo.goodshop.file_service.mappers;

import com.rekahdo.goodshop.file_service.dtos.clients.ApiClient;
import com.rekahdo.goodshop.file_service.dtos.entities.ApiDto;
import com.rekahdo.goodshop.file_service.entities.ApiEntity;
import org.mapstruct.*;

import java.util.List;

public interface TripleMapper<
		ENTITY extends ApiEntity,
		DTO extends ApiDto,
		CLIENT extends ApiClient> {

	DTO toDto(ENTITY entity);
	CLIENT toClient(ENTITY entity);
	List<DTO> toDtoList(List<ENTITY> entities);
	List<CLIENT> toClientList(List<ENTITY> entities);

	@BeforeMapping
	default void beforeMappingToDto(@MappingTarget DTO target, ENTITY source){};

	@AfterMapping
	default void afterMappingToDto(@MappingTarget DTO target, ENTITY source){};

	@BeforeMapping
	default void beforeMappingToClient(@MappingTarget CLIENT target, ENTITY source){};

	@AfterMapping
	default void afterMappingToClient(@MappingTarget CLIENT target, ENTITY source){};

}
