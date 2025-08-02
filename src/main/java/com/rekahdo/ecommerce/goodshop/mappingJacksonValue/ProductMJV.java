package com.rekahdo.ecommerce.goodshop.mappingJacksonValue;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public final class ProductMJV extends MJV{

	public ProductMJV() {
		LIST_FILTER = new SimpleFilterProvider()
				.addFilter("productDtoFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name", "price", "_links"));

		SELF = new SimpleFilterProvider()
				.addFilter("productDtoFilter", SimpleBeanPropertyFilter.serializeAllExcept("categoryId"));
	}

}
