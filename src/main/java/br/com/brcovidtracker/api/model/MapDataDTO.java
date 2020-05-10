package br.com.brcovidtracker.api.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapDataDTO {
	
	private LocalDate date;
	private String country;
	private String state;
	private String name;
	private String capital;
	private double lat;
	private double lon;
	private int newDeaths;
	private int deaths;
	private int newCases;
	private int totalCases;

}
