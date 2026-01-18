package com.solitawebappbackend.demo.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.solitawebappbackend.demo.models.Electricity;

public interface ElectricityRepository extends JpaRepository<Electricity, Long> {

    @Query(
      value = "SELECT DISTINCT e.date FROM Electricity e",
      countQuery = "SELECT COUNT(DISTINCT e.date) FROM Electricity e"
    )
    Page<LocalDate> findDistinctDates(Pageable pageable);

    List<Electricity> findByDate(LocalDate date, Sort sort);

    List<Electricity> findByDateOrderByStartTimeDesc(LocalDate date);

    @Query(value = "SELECT DISTINCT date FROM public.electricitydata ORDER BY date DESC LIMIT :limit OFFSET :offset",
           nativeQuery = true)
    List<LocalDate> findDistinctDatesNative(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT * FROM public.electricitydata WHERE date = :date ORDER BY starttime DESC",
           nativeQuery = true)
    List<Electricity> findByDateNative(@Param("date") LocalDate date);
}

