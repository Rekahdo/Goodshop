package com.rekahdo.goodshop.file_service.dtos.entities;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Set;

@Relation(collectionRelation = "items")
public class EntityDto<T extends EntityDto<T>> extends RepresentationModel<T> implements ApiDto {

    private static FilterProvider showFew;
    private static FilterProvider showAll;

    EntityDto(String filterId, Set<String> showFew){
        EntityDto.showFew = new SimpleFilterProvider().addFilter(filterId,
                SimpleBeanPropertyFilter.filterOutAllExcept(showFew));

        showAll = new SimpleFilterProvider().addFilter(filterId,
                SimpleBeanPropertyFilter.serializeAll());
    }

    public static <T> MappingJacksonValue showFew(T list) {
        if(showFew == null) throw new NullPointerException();

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        mappingJacksonValue.setFilters(showFew);
        return mappingJacksonValue;
    }

    public static <T> MappingJacksonValue showAll(T single) {
        if(showAll == null) throw new NullPointerException();

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(single);
        mappingJacksonValue.setFilters(showAll);
        return mappingJacksonValue;
    }
}