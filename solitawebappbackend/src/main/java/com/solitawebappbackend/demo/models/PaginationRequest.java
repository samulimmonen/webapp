package com.solitawebappbackend.demo.models;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
public class PaginationRequest {
	
	private Integer page;
	
	private Integer size;
	
	private String sortField;
	
	@Builder.Default
	private Sort.Direction direction = Sort.Direction.DESC;

}
