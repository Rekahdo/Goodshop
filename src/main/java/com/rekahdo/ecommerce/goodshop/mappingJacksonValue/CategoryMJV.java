package com.rekahdo.ecommerce.goodshop.mappingJacksonValue;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public final class CategoryMJV extends MJV{

	public CategoryMJV() {
        LIST_FILTER = new SimpleFilterProvider()
				.addFilter("categoryDtoFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name", "_links"));

		SELF = new SimpleFilterProvider()
				.addFilter("categoryDtoFilter", SimpleBeanPropertyFilter.serializeAll());
	}

}
