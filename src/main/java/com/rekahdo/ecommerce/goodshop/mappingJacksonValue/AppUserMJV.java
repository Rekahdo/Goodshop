package com.rekahdo.ecommerce.goodshop.mappingJacksonValue;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public final class AppUserMJV extends MJV{

	public AppUserMJV() {
		LIST_FILTER = new SimpleFilterProvider().addFilter("appUserDtoFilter",
				SimpleBeanPropertyFilter.filterOutAllExcept("username", "_links"));

		SELF = new SimpleFilterProvider().addFilter("appUserDtoFilter",
				SimpleBeanPropertyFilter.serializeAllExcept("adminKey", "password"));
	}

}
