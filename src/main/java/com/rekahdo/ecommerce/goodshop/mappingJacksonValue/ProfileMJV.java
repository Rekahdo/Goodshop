package com.rekahdo.ecommerce.goodshop.mappingJacksonValue;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public final class ProfileMJV extends MJV{

	public ProfileMJV() {
		SELF = new SimpleFilterProvider()
				.addFilter("profileDtoFilter", SimpleBeanPropertyFilter.serializeAllExcept("country"));
	}

}
