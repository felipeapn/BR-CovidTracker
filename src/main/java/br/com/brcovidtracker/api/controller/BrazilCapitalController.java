package br.com.brcovidtracker.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brcovidtracker.api.model.BrazilCapital;
import br.com.brcovidtracker.api.service.BrazilCapitalService;

@RestController
@RequestMapping("capitals")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BrazilCapitalController {
	
	@Autowired
	private BrazilCapitalService brazilCapitalService;
	
	@GetMapping
	public List<BrazilCapital> getBrazilCapitals() {
		return brazilCapitalService.getCapitals();
	}
	

}
