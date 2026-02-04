package com.rekahdo.goodshop.vendor_service.dtos.assemblers;

import com.rekahdo.goodshop.vendor_service.dtos.paginations.ApiPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public abstract class ApiAssembler<T> implements RepresentationModelAssembler<T, EntityModel<T>> {

    private final Object invocationValue;

    public ApiAssembler(Object invocationValue) {
        this.invocationValue = invocationValue;
    }

    public PagedModel<EntityModel<T>> toPagedModel(Page<T> page, ApiPageRequest<T> pageRequest){
        List<EntityModel<T>> entityModels = page.getContent().stream()
                .map(this::toModel).toList();

        PagedModel<EntityModel<T>> pagedModel = PagedModel.of(entityModels,
                new PagedModel.PageMetadata(
                        page.getSize(), page.getNumber(),
                        page.getTotalElements(), page.getTotalPages()
                )
        );

        int lastPage = page.getTotalPages() - 1;
        int pageNo = page.getNumber();

        if(pageNo >= page.getTotalPages())
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    String.format("Page No should be a maximum value of %d", lastPage));

        if(page.hasNext()) {
            pageRequest.setPageNo(pageNo + 1);
            pagedModel.add(link(pageRequest, "next"));
        }

        if(page.hasPrevious()) {
            pageRequest.setPageNo(pageNo - 1);
            pagedModel.add(link(pageRequest, "prev"));
        }

        if(page.getNumber() != 0) { // If pageNo is not the first page, add like to first page
            pageRequest.setPageNo(0);
            pagedModel.add(link(pageRequest, "first"));
        }

        if(page.getNumber() != lastPage) { // If pageNo is not the last page, add link to last page{
            pageRequest.setPageNo(lastPage);
            pagedModel.add(link(pageRequest, "last"));
        }

        return pagedModel;
    }

    private Link link(ApiPageRequest<T> pageRequest, String relation) {
        UriComponentsBuilder builder = linkTo(invocationValue).toUriComponentsBuilder()
                .queryParam("pageNo", pageRequest.getPageNo())
                .queryParam("size", pageRequest.getSize())
                .queryParam("ascend", pageRequest.isAscend())
                .queryParam("sortByField", pageRequest.getSortByField());
        return Link.of(builder.build().toUriString(), relation);
    }

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