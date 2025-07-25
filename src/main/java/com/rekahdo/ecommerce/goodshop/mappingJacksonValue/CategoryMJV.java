package com.rekahdo.ecommerce.goodshop.mappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public final class CategoryMJV {

	private static final FilterProvider FILTER = new SimpleFilterProvider()
			.addFilter("categoryDtoFilter", SimpleBeanPropertyFilter.serializeAll());

	public static <T> MappingJacksonValue filter(T dto) {
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dto);
		mappingJacksonValue.setFilters(FILTER);
		return mappingJacksonValue;
	}

}
