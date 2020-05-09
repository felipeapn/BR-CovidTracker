package br.com.brcovidtracker.api.service;

import java.time.LocalDate;
import java.util.List;

import br.com.brcovidtracker.api.model.DaylyVirusData;
import br.com.brcovidtracker.api.model.DaylyVirusDataFilter;

public interface CovidDataService {

	List<DaylyVirusData> getDaylyVirusData(DaylyVirusDataFilter filter);

	DaylyVirusData getLastDayData();

	LocalDate getLastDay();

	DaylyVirusData getSum(DaylyVirusDataFilter filter);

}
