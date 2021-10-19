package com.capgemini.service;

import org.springframework.http.ResponseEntity;

import com.capgemini.entities.Airport;

public interface AirportService {
	public Iterable<Airport> viewAllAirport();
	public Airport viewAirport(String airportCode);
	public ResponseEntity<Airport> addAirport(Airport airport);
	public Airport modifyAirport(Airport airport);
	public String removeAirport(String airportCode);
}