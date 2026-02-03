package com.rekahdo.goodshop.vendor_service.dtos.paginations;

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
public abstract class ApiPageRequest<T> {

	protected Integer pageNo = 0;
	protected Integer size = 10;
	protected boolean ascend = true;
	protected String sortByField = "id";

	private final Object invocationValue;

	public ApiPageRequest(Object invocationValue) {
		this.invocationValue = invocationValue;
	}

	public Pageable getPageable(){
		Sort.Direction sort = (ascend ? Sort.Direction.ASC : Sort.Direction.DESC);
		return PageRequest.of(pageNo, size, sort, StringUtils.split(sortByField, ","));
	}

	public Page<T> getPaged(List<T> items){
		// PagedListHolder
		PagedListHolder<T> pagedListHolder = new PagedListHolder<>(items);
		pagedListHolder.setPage(this.getPageNo());
		pagedListHolder.setPageSize(this.getSize());

		// Property Comparator
		List<T> pageSlice = pagedListHolder.getPageList();
		boolean ascending = this.isAscend();
		PropertyComparator.sort(pageSlice, new MutableSortDefinition(this.getSortByField(), true, ascending));

		// PageImpl
		return new PageImpl<>(pageSlice, this.getPageable(), items.size());
	}

	public PagedModel<T> getPagedModel(Page<T> page){
		PagedModel<T> pagedModel = PagedModel.of(page.getContent(),
				new PagedModel.PageMetadata(page.getSize(), page.getNumber(),
						page.getTotalElements(), page.getTotalPages())
		);

		int lastPage = page.getTotalPages() - 1;
		int pageNo = page.getNumber();

		if(pageNo >= page.getTotalPages())
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
					String.format("Page No should be a maximum value of %d", lastPage));

		if(page.hasNext()) {
			setPageNo(pageNo + 1);
			pagedModel.add(link("next"));
		}

		if(page.hasPrevious()) {
			setPageNo(pageNo - 1);
			pagedModel.add(link("prev"));
		}

		if(page.getNumber() != 0) { // If pageNo is not the first page, add like to first page
			setPageNo(0);
			pagedModel.add(link("first"));
		}

		if(page.getNumber() != lastPage) { // If pageNo is not the last page, add link to last page{
			setPageNo(lastPage);
			pagedModel.add(link("last"));
		}

		return pagedModel;
	}

	private Link link(String relation) {
		UriComponentsBuilder builder = linkTo(invocationValue).toUriComponentsBuilder()
				.queryParam("pageNo", pageNo)
				.queryParam("size", size)
				.queryParam("ascend", isAscend())
				.queryParam("sortByField", sortByField);
		return Link.of(builder.build().toUriString(), relation);
	}

}