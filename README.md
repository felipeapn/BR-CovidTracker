# BR-CovidTracker
API to retrieve Brazil COVID-19 data.
Frontend to show these statistics on Charts and on Maps.

https://br-covid-tracker.herokuapp.com/

## Backend - Java / Spring 
I've used a lot of the new technologies released on Java 8 as Functional Interfaces, Lambdas, Streams, and LocalDate API. Check this class below:

https://github.com/felipeapn/BR-CovidTracker/blob/master/src/main/java/br/com/brcovidtracker/api/service/impl/CovidDataServiceImpl.java

## Frontend - Angular 8 / PrimeNG
Souce code: https://github.com/felipeapn/BR-CovidTracker-Angular

## Endpoints

All these endpoints can be used as fallow:
On web: https://br-covid-tracker.herokuapp.com/daylydata
Locally: http://localhost:8080/daylydata

### 1) daylydata

Filters:

city -> Name followed by "/State Abbreviation"
/daylydata?city=S%C3%A3o%20Paulo/SP

state -> State Abbreviation.
/daylydata?state=SP

fromDay e toDay -> To select period
/daylydata?fromDay=2020-01-29&toDay=2020-02-29

**All these filters can be used combined.
**city or state equal to "TOTAL" informs the total of Brazil on that day.

### 2) capitals
To get all states and its capital.

### 3) mapdata
To get Lat and Lon to set map points. On mouse over on each point its possible see the statistics on that state.

### 4) /data/lastDay
To get the lastDay Brazil data. 

## Source

### Data source
https://github.com/wcota/covid19br
https://raw.githubusercontent.com/wcota/covid19br/master/cases-brazil-cities-time.csv
