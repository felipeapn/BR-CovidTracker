package br.com.brcovidtracker.api.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DaylyVirusData {
	
	private LocalDate date;
	private String country;
	private String state;
	private String city;
	private String ibgeID;
	private int newDeaths;
	private int deaths;
	private int newCases;
	private int totalCases;
	private double deathsPer100kInhabitants;
	private double totalCasesPer100kInhabitants;
	private double deathsByTotalCases;

}
