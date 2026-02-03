package com.rekahdo.goodshop.profile_service.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface CrossMapper<FIRST, SECOND> {

	FIRST toFirst(SECOND second);
	SECOND toSecond(FIRST first);
	List<FIRST> toFirstList(List<SECOND> seconds);
	List<SECOND> toSecondList(List<FIRST> firsts);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateFirst(SECOND source, @MappingTarget FIRST target);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateSecond(FIRST source, @MappingTarget SECOND target);

	@AfterMapping
	default void afterMappingToFirst(@MappingTarget FIRST target, SECOND source){};

	@AfterMapping
	default void afterMappingToSecond(@MappingTarget SECOND target, FIRST source){};

}
