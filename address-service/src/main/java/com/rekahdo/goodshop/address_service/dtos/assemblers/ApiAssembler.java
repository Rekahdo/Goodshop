package com.rekahdo.goodshop.address_service.dtos.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public abstract class ApiAssembler<T> implements RepresentationModelAssembler<T, EntityModel<T>> {

    protected Link link(WebMvcLinkBuilder linkBuilder, String relation, HttpMethod method){
        return linkBuilder.withRel(relation).withType(method.name());
    }

    protected Link link(WebMvcLinkBuilder linkBuilder, HttpMethod method){
        return linkBuilder.withSelfRel().withType(method.name());
    }

    protected Link link(WebMvcLinkBuilder linkBuilder, HttpMethod method, MediaType input, MediaType output){
        return Affordances.of(linkBuilder.withSelfRel()).afford(method)
                .withInput(input.getClass()).withOutput(output.getClass())
                .build().toLink().withSelfRel();
    }

    protected Link link(WebMvcLinkBuilder linkBuilder, String relation, HttpMethod method, MediaType input, MediaType output){
        return Affordances.of(linkBuilder.withSelfRel()).afford(method)
                .withInput(input.getClass()).withOutput(output.getClass())
                .build().toLink().withRel(relation);
    }

}