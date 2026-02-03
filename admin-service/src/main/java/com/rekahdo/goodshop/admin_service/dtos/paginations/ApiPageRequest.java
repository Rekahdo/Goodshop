package com.rekahdo.goodshop.admin_service.dtos.paginations;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Getter
@Setter
public abstract class ApiPageRequest {

	protected Integer pageNo = 0;
	protected Integer size = 10;
	protected boolean ascend = true;
	protected String sortByField = "id";

	public Pageable getPageable(){
		Sort.Direction sort = (ascend ? Sort.Direction.ASC : Sort.Direction.DESC);
		return PageRequest.of(pageNo, size, sort, StringUtils.split(sortByField, ","));
	}

	public <OBJECT> Page<OBJECT> getPagedList(List<OBJECT> items){
		// PagedListHolder
		PagedListHolder<OBJECT> pagedListHolder = new PagedListHolder<>(items);
		pagedListHolder.setPage(this.getPageNo());
		pagedListHolder.setPageSize(this.getSize());

		// Property Comparator
		List<OBJECT> pageSlice = pagedListHolder.getPageList();
		boolean ascending = this.isAscend();
		PropertyComparator.sort(pageSlice, new MutableSortDefinition(this.getSortByField(), true, ascending));

		// PageImpl
		return new PageImpl<>(pageSlice, this.getPageable(), items.size());
	}

	public <OBJECT> PagedModel<OBJECT> getPagedModel(Page<OBJECT> pageDto, Object invocationValue){
		PagedModel<OBJECT> pagedModel = PagedModel.of(pageDto.getContent(),
				new PagedModel.PageMetadata(pageDto.getSize(), pageDto.getNumber(),
						pageDto.getTotalElements(), pageDto.getTotalPages())
		);

		int pageCount = pageDto.getTotalPages();
		int lastPage = pageCount-1;

		if(pageNo >= pageCount)
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
					String.format("Page No should be a maximum value of %d", lastPage));

		if(pageDto.hasNext())
			pagedModel.add(buildLink(pageNo+1, "next", invocationValue));

		if(pageDto.hasPrevious())
			pagedModel.add(buildLink(pageNo-1, "prev", invocationValue));

		if(pageDto.getNumber() != 0) // If pageNo is not the first page, add like to first page
			pagedModel.add(buildLink(0, "first", invocationValue));

		if(pageDto.getNumber() != lastPage) // If pageNo is not the last page, add link to last page
			pagedModel.add(buildLink(lastPage, "last", invocationValue));

		return pagedModel;
	}

	private Link buildLink(Integer pageNo, String relation, Object invocationValue) {
		UriComponentsBuilder builder = linkTo(invocationValue).toUriComponentsBuilder()
				.queryParam("pageNo", pageNo)
				.queryParam("size", size)
				.queryParam("ascend", ascend)
				.queryParam("sortByField", sortByField);
		return Link.of(builder.build().toUriString(), relation);
	}

}