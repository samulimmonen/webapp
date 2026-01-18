package com.solitawebappbackend.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.solitawebappbackend.demo.models.Electricity;
import com.solitawebappbackend.demo.models.PagedResult;
import com.solitawebappbackend.demo.models.PaginationRequest;
import com.solitawebappbackend.demo.models.PaginationUtils;
import com.solitawebappbackend.demo.repository.ElectricityRepository;

@Service
public class ElectricityService {
	private final ElectricityRepository electricityRepository;
	
	@Autowired
	public ElectricityService(ElectricityRepository electricityRepository) {
		this.electricityRepository = electricityRepository;
	}
	
	public PagedResult<Electricity> getAllElectricityData(PaginationRequest request) {
		final Pageable pageable = PaginationUtils.getPageable(request.getPage(), request.getSize(), request.getDirection(), request.getSortField());
		final Page<Electricity> entities = electricityRepository.findAll(pageable);
		return new PagedResult<Electricity>(entities.getContent(), entities.getTotalPages(), entities.getTotalElements(), entities.getSize(), entities.getNumber(), entities.getSize() <= 0 ? false : true);
	}

	
}
