package br.com.brcovidtracker.api.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brcovidtracker.api.model.DaylyVirusData;
import br.com.brcovidtracker.api.model.DaylyVirusDataFilter;
import br.com.brcovidtracker.api.model.MapData;
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
	public List<MapData> getMapData() {
		
		LocalDate lastDate = this.covidDataService.getLastDay();
		
		DaylyVirusDataFilter filter = new DaylyVirusDataFilter();
		
		MapData mapData = new MapData();
		List<MapData> mapDatas = new ArrayList<MapData>();
		
		filter.setFromDay(lastDate.toString());
		
		this.brazilCapitalService.getCapitals()
			.forEach(capital -> {
				filter.setState(capital.getInitial());
				mapData.setDaylyVirusData(this.covidDataService.getSum(filter));
				mapData.setBrazilCapital(capital);
				mapDatas.add(mapData);
			});
		
		return mapDatas;
	}

}
