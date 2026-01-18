package com.solitawebappbackend.demo.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ElectricityByDateCollection {
	private LocalDate Date;
	private List<Electricity> Content;
	
	public ElectricityByDateCollection(LocalDate date, List<Electricity> content) {
		super();
		Date = date;
		Content = content;
	}
}
