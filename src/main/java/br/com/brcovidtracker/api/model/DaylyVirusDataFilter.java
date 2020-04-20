package br.com.brcovidtracker.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DaylyVirusDataFilter {
	
	private String fromDay;
	private String toDay;
	private String country;
	private String state;
	private String city;
	
}
