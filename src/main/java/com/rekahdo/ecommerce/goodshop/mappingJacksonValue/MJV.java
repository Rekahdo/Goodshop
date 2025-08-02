package com.rekahdo.ecommerce.goodshop.mappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public abstract class MJV {

	protected FilterProvider LIST_FILTER;
	protected FilterProvider SELF;

	public <T> MappingJacksonValue listFilter(T dto) {
		if(LIST_FILTER == null)
			throw new NullPointerException();

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dto);
		mappingJacksonValue.setFilters(this.LIST_FILTER);
		return mappingJacksonValue;
	}

	public <T> MappingJacksonValue selfFilter(T dto) {
		if(SELF == null)
			throw new NullPointerException();

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dto);
		mappingJacksonValue.setFilters(this.SELF);
		return mappingJacksonValue;
	}

}
