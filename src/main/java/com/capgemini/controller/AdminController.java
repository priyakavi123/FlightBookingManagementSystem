package com.capgemini.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.ScheduledFlightNotFoundException;
import com.capgemini.entities.ScheduledFlight;
import com.capgemini.service.ScheduledFlightService;

@RestController
@RequestMapping("/flight")
public class AdminController {
	@Autowired
	ScheduledFlightService scheduleFlightService;

	
	@PostMapping("/scheduleflight/add")
	public ScheduledFlight addSF(@RequestBody ScheduledFlight scheduledFlight){
	  return scheduleFlightService.addScheduledFlight(scheduledFlight);
	}
		
	@PutMapping("/scheduleflight/modify")
	public ScheduledFlight modifyScheduleFlight(@RequestBody ScheduledFlight scheduleFlight) {
	
		return scheduleFlightService.modifyScheduledFlight(scheduleFlight);
	}
	
	@DeleteMapping("/scheduleflight/delete")
	public String deleteSF(@RequestParam BigInteger flightId) throws RecordNotFoundException {
		return scheduleFlightService.removeScheduledFlight(flightId);
	}

	@GetMapping("/scheduleflight/search")
	@ExceptionHandler(ScheduledFlightNotFoundException.class)
	public ResponseEntity<ScheduledFlight> viewSF(@RequestParam BigInteger flightId) throws ScheduledFlightNotFoundException {
		ScheduledFlight searchSFlight = scheduleFlightService.viewScheduledFlight(flightId);
		if (searchSFlight == null) {
			return new ResponseEntity("Flight not present", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<ScheduledFlight>(searchSFlight, HttpStatus.OK);
		}
	}

	@GetMapping("/scheduleflight/viewAll")
	public Iterable<ScheduledFlight> viewAllSF() {
		return scheduleFlightService.viewAllScheduledFlights();
	}
	
}
