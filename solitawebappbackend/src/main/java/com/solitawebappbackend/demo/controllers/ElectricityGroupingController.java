package com.solitawebappbackend.demo.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.solitawebappbackend.demo.models.ElectricityByDateCollection;
import com.solitawebappbackend.demo.models.PagedResult;
import com.solitawebappbackend.demo.services.ElectricityGroupingService;

@RestController
@RequestMapping("/api/electricity")
public class ElectricityGroupingController {

    private final ElectricityGroupingService groupingService;

    public ElectricityGroupingController(ElectricityGroupingService groupingService) {
        this.groupingService = groupingService;
    }

    @GetMapping("/grouped")
    public ResponseEntity<PagedResult<ElectricityByDateCollection>> getGrouped(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        var result = groupingService.getGroupedDateBucketsDb(page, size, direction);
        return ResponseEntity.ok(result);
    }
}

