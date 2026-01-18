package com.solitawebappbackend.demo.models;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagedResult<T> {
	
	private Collection<T> content;
	private Integer totalPages;
	private long totalElements;
	private Integer size;
	private Integer page;
	private boolean empty;
	
	public PagedResult(Collection<T> content, Integer totalPages, long totalElements, Integer size, Integer page,
			boolean empty) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.size = size;
		this.page = page;
		this.empty = empty;
	}
}
