package com.solitawebappbackend.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solitawebappbackend.demo.models.Electricity;
import com.solitawebappbackend.demo.models.PagedResult;
import com.solitawebappbackend.demo.models.PaginationRequest;
import com.solitawebappbackend.demo.services.ElectricityService;

@RestController
@RequestMapping(path = "api/electricity")
public class ElectricityController {
	
	private final ElectricityService electricityService;
	
	@Autowired
	public ElectricityController(ElectricityService electricityService) {
		this.electricityService = electricityService;
	}
	
	@GetMapping(path = "allData")
	public PagedResult<Electricity> getAllData
	(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(defaultValue = "id") String sortField,
        @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) 
	{
		final PaginationRequest request = new PaginationRequest(page, size, sortField, direction);
        final PagedResult<Electricity> electricityData = electricityService.getAllElectricityData(request);
        return electricityData;
	}
}
