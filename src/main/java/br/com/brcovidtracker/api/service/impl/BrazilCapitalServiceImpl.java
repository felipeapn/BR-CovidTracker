package br.com.brcovidtracker.api.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brcovidtracker.api.model.BrazilCapital;
import br.com.brcovidtracker.api.service.BrazilCapitalService;

@Service
public class BrazilCapitalServiceImpl implements BrazilCapitalService{
	
	private List<BrazilCapital> capitals;
	
	
	public List<BrazilCapital> getCapitals() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			this.capitals = Arrays.asList(objectMapper.readValue(
					new File("src/main/resources/static/brazil-capitals.json"), BrazilCapital[].class));		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.capitals;
	}
}
