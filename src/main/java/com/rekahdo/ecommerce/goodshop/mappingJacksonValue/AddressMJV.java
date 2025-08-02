package com.rekahdo.ecommerce.goodshop.mappingJacksonValue;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public final class AddressMJV extends MJV{

	public AddressMJV() {
		SELF = new SimpleFilterProvider()
				.addFilter("addressDtoFilter", SimpleBeanPropertyFilter.serializeAll());
	}

}
