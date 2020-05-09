package br.com.brcovidtracker.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brcovidtracker.api.model.MapData;
import br.com.brcovidtracker.api.service.MapDataServide;

@RestController
@RequestMapping("mapdata")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class MapDataController {

	@Autowired
	private MapDataServide mapDataServide;

	@GetMapping
	public List<MapData> getMapData () {
		
		return this.mapDataServide.getMapData();
	}
}
