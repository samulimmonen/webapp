package com.solitawebappbackend.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.solitawebappbackend.demo.models.Electricity;
import com.solitawebappbackend.demo.models.ElectricityByDateCollection;
import com.solitawebappbackend.demo.models.PagedResult;
import com.solitawebappbackend.demo.repository.ElectricityRepository;

@Service
public class ElectricityGroupingService {

    private final ElectricityRepository repo;

    public ElectricityGroupingService(ElectricityRepository repo) {
        this.repo = repo;
    }

    public PagedResult<ElectricityByDateCollection> getGroupedDateBucketsDb(int page, int size, Sort.Direction direction) {
        // 1) page distinct dates
        Pageable datePageable = PageRequest.of(page, size, Sort.by(direction, "date"));
        var datePage = repo.findDistinctDates(datePageable); // Page<LocalDate>

        // 2) for each date, fetch rows for that date (ordered by StartTime)
        Sort rowSort = Sort.by(direction, "startTime"); // or Sort.by(direction, "startTime")
        List<ElectricityByDateCollection> buckets = datePage.getContent().stream()
            .map(d -> {
                List<Electricity> rows = repo.findByDate(d, rowSort);
                return new ElectricityByDateCollection(d, rows);
            })
            .collect(Collectors.toList());

        // 3) wrap into your PagedResult (adapt constructor to your class)
        return new PagedResult<>(
            buckets,
            datePage.getTotalPages(),
            datePage.getTotalElements(),
            datePage.getSize(),
            datePage.getNumber(),
            !buckets.isEmpty()
        );
    }
}

