# BR-CovidTracker
API para recuperar os dados da Covid-19 no Brasil em um CSV e passar via Json com filtros

## Fonte

https://github.com/wcota/covid19br

https://raw.githubusercontent.com/wcota/covid19br/master/cases-brazil-cities-time.csv

## Formato do Json

{
"date": "2020-02-25",
"country": "Brazil",
"state": "SP",
"city": "São Paulo/SP",
"ibgeID": "3550308",
"newDeaths": 0,
"deaths": 0,
"newCases": 1,
"totalCases": 1
}

## Filtros URL

city -> Nome da cidade seguida de "/sigla estado"
http://localhost:8080/daylydata?city=S%C3%A3o%20Paulo/SP

state -> Sigla do Estado.
http://localhost:8080/daylydata?state=SP

fromDay e toDay -> para selecionar periodo
http://localhost:8080/daylydata?fromDay=2020-01-29&toDay=2020-02-29

Obs.: city ou state igual a "TOTAL" informa o total do Brasil naque dia.
