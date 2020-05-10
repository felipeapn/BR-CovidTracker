package br.com.brcovidtracker.api.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brcovidtracker.api.model.DaylyVirusData;
import br.com.brcovidtracker.api.model.DaylyVirusDataFilter;
import br.com.brcovidtracker.api.model.MapDataDTO;
import br.com.brcovidtracker.api.service.BrazilCapitalService;
import br.com.brcovidtracker.api.service.CovidDataService;
import br.com.brcovidtracker.api.service.MapDataServide;

@Service
public class MapDataServiceImpl implements MapDataServide {

	@Autowired
	private CovidDataService covidDataService;
	
	@Autowired 
	private BrazilCapitalService brazilCapitalService;

	@Override
	public List<MapDataDTO> getMapData() {
		
		LocalDate lastDate = this.covidDataService.getLastDay();
		
		DaylyVirusDataFilter filter = new DaylyVirusDataFilter();
		
		//MapDataDTO mapData = new MapDataDTO();
		List<MapDataDTO> mapDatas = new ArrayList<MapDataDTO>();
		
		filter.setFromDay(lastDate.toString());
		
		this.brazilCapitalService.getCapitals()
			.forEach(capital -> {
				filter.setState(capital.getInitial());
				DaylyVirusData sumByState = this.covidDataService.getSum(filter);
				
				MapDataDTO mapData = new MapDataDTO(
						sumByState.getDate(),
						sumByState.getCountry(),
						sumByState.getState(),
						capital.getName(),
						capital.getCapital(),
						capital.getLat(),
						capital.getLon(),
						sumByState.getNewDeaths(),
						sumByState.getDeaths(),
						sumByState.getNewCases(),
						sumByState.getTotalCases()
						);
				
				mapDatas.add(mapData);
			});
		
		return mapDatas;
	}

}
