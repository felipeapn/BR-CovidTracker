package br.com.brcovidtracker.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brcovidtracker.api.model.DaylyVirusData;
import br.com.brcovidtracker.api.model.DaylyVirusDataFilter;
import br.com.brcovidtracker.api.service.CovidDataService;

@RestController
@RequestMapping("daylydata")
public class DaylyDataController {

	@Autowired
	private CovidDataService covidDataService;
	
	@GetMapping
	public List<DaylyVirusData> getDaylyVirusData (DaylyVirusDataFilter filter) {
		return covidDataService.getDaylyVirusData(filter);
	}
}
