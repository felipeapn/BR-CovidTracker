package br.com.brcovidtracker.api.model;

import lombok.Data;

@Data
public class BrazilCapital {
	
	private String initial;
	private String name;
	private String capital;
	private double lat;
	private double lon;
}
