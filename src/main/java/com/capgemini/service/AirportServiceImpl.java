package com.capgemini.service;


import java.util.Optional;
import com.capgemini.entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.capgemini.dao.AirportRepository;
import com.capgemini.exception.RecordAlreadyPresentException;
import com.capgemini.exception.RecordNotFoundException;

@Service
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportRepository airportRepository;

	@Override
	public Iterable<Airport> viewAllAirport() {
		return airportRepository.findAll();
	}

	@Override
	public Airport viewAirport(String airportCode) {
		Optional<Airport> findById = airportRepository.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		}
		else
			throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	    }
	
	@Override
	public ResponseEntity<Airport> addAirport(Airport airport) {
		
		Optional<Airport> findById = airportRepository.findById(airport.getAirportCode());
		try {
		if (!findById.isPresent()) {
			airportRepository.save(airport);
			return new ResponseEntity<Airport>(airport,HttpStatus.OK);
		} 
		else
			throw new RecordAlreadyPresentException(
					"Airport with code : " + airport.getAirportCode() + " already present");
	     }
		catch(RecordAlreadyPresentException e)
		{
			return new ResponseEntity<Airport>(airport,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Airport modifyAirport(Airport airport) {
		Optional<Airport> findById = airportRepository.findById(airport.getAirportCode());
		if (findById.isPresent()) {
			airportRepository.save(airport);
		} 
		else
			throw new RecordNotFoundException("Airport with code: " + airport.getAirportCode() + " not exists");
		return airport;
	}

	@Override
	public String removeAirport(String airportCode) {
		Optional<Airport> findById = airportRepository.findById(airportCode);
		if (findById.isPresent()) {
			airportRepository.deleteById(airportCode);
			return "Airport removed";
		} else
			throw new RecordNotFoundException("Airport with code: " + airportCode + " not exists");

	}
}
