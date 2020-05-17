	package br.com.brcovidtracker.api.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.brcovidtracker.api.model.DaylyVirusData;
import br.com.brcovidtracker.api.model.DaylyVirusDataFilter;
import br.com.brcovidtracker.api.service.CovidDataService;

@Service
public class CovidDataServiceImpl implements CovidDataService {

	private static final String DATA_URL = "https://raw.githubusercontent.com/wcota/covid19br/master/cases-brazil-cities-time.csv";

	private List<DaylyVirusData> virusDataList;

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fechVirusData() throws IOException, InterruptedException {

		this.virusDataList = new ArrayList<DaylyVirusData>();

		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

		StringReader csvReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for (CSVRecord record : records) {
			DaylyVirusData daylyVirusData = new DaylyVirusData();

			daylyVirusData.setDate(LocalDate.parse(record.get("date")));
			daylyVirusData.setCountry(record.get("country"));
			daylyVirusData.setState(record.get("state"));
			daylyVirusData.setCity(record.get("city"));
			daylyVirusData.setIbgeID(record.get("ibgeID"));
			daylyVirusData.setNewDeaths(Integer.parseInt(record.get("newDeaths")));
			daylyVirusData.setDeaths(Integer.parseInt(record.get("deaths")));
			daylyVirusData.setNewCases(Integer.parseInt(record.get("newCases")));
			daylyVirusData.setTotalCases(Integer.parseInt(record.get("totalCases")));
			daylyVirusData.setDeathsPer100kInhabitants(Double.parseDouble(record.get("deaths_per_100k_inhabitants")));
			daylyVirusData.setTotalCasesPer100kInhabitants(Double.parseDouble(record.get("totalCases_per_100k_inhabitants")));
			daylyVirusData.setDeathsByTotalCases(Double.parseDouble(record.get("deaths_by_totalCases")));
			
			this.virusDataList.add(daylyVirusData);
		}

	}

	//TODO: Ajustar unmodified list.
	public List<DaylyVirusData> getDaylyVirusData(DaylyVirusDataFilter filter) {

		List<Predicate<DaylyVirusData>> allPredicates = this.createPredicate(filter);

		return this.virusDataList.stream()
								 .filter(allPredicates.stream()
										 			  .reduce(x -> true, Predicate::and))
								 .collect(Collectors.toList());
	}

	private List<Predicate<DaylyVirusData>> createPredicate(DaylyVirusDataFilter filter) {
			
		List<Predicate<DaylyVirusData>> allPredicates = new ArrayList<>();

		if (filter.getFromDay() != null && !filter.getFromDay().isEmpty()) {
			allPredicates.add(
					element -> element.getDate().isAfter(LocalDate.parse(filter.getFromDay()).minusDays(1)));
		}
		
		if (filter.getToDay() != null && !filter.getToDay().isEmpty()) {
			allPredicates.add(
					element -> element.getDate().isBefore(LocalDate.parse(filter.getToDay()).plusDays(1)));
		}

		if (filter.getCountry() != null && !filter.getCountry().isEmpty()) {
			allPredicates.add(element -> element.getCountry().compareToIgnoreCase(filter.getCountry()) == 0);
		}

		if (filter.getState() != null && !filter.getState().isEmpty()) {
			allPredicates.add(element -> element.getState().compareToIgnoreCase(filter.getState()) == 0);
		}

		if (filter.getCity() != null && !filter.getCity().isEmpty()) {
			allPredicates.add(element -> element.getCity().compareToIgnoreCase(filter.getCity()) == 0);
		}
		
		return allPredicates;
	}

	public DaylyVirusData getLastDayData() {
		return this.virusDataList.get(this.virusDataList.size() - 1);
	}
	
	public LocalDate getLastDay() {
		return this.getLastDayData().getDate();
	}

	@Override
	public DaylyVirusData getSum(DaylyVirusDataFilter filter) {
		
		List<Predicate<DaylyVirusData>> allPredicates = this.createPredicate(filter);
		
		DaylyVirusData sumState = new DaylyVirusData();
		sumState.setCountry("Brazil");
		sumState.setCity("TOTAL");
		sumState.setState(filter.getState());
		sumState.setDate(LocalDate.parse(filter.getFromDay()));
		
		this.virusDataList.stream().filter(allPredicates.stream().reduce(x -> true, Predicate::and))
				.forEach(daylyData -> {
					sumState.setTotalCases(sumState.getTotalCases() + daylyData.getTotalCases());
					sumState.setNewCases(sumState.getNewCases() + daylyData.getNewCases());
					sumState.setDeaths(sumState.getDeaths() + daylyData.getDeaths());
					sumState.setNewDeaths(sumState.getNewDeaths() + daylyData.getNewDeaths());
				});
		
		return sumState;
	}

}
