package com.capgemini.service;

import java.math.BigInteger;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.ScheduledFlightNotFoundException;
import com.capgemini.entities.ScheduledFlight;

public interface ScheduledFlightService {
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);
	public String removeScheduledFlight(BigInteger id) throws RecordNotFoundException;
	public Iterable<ScheduledFlight> viewAllScheduledFlights();
	public ScheduledFlight viewScheduledFlight(BigInteger id) throws ScheduledFlightNotFoundException;
	

}
