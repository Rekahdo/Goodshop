package com.rekahdo.ecommerce.goodshop.utilities;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Filters {

	public static final FilterProvider APP_USER_DTO_FILTER_PUBLIC = new SimpleFilterProvider().addFilter("appUserDtoFilter",
			SimpleBeanPropertyFilter.filterOutAllExcept("id", "username", "roles", "_links"));

	public static final FilterProvider APP_USER_DTO_FILTER_PRIVATE = new SimpleFilterProvider().addFilter("appUserDtoFilter",
			SimpleBeanPropertyFilter.serializeAllExcept("password"));

}
