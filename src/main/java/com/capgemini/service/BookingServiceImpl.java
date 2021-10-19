package com.capgemini.service;


import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.exception.RecordAlreadyPresentException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.dao.BookingRepository;
import com.capgemini.entities.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public ResponseEntity<Booking> createBooking(Booking newBooking) {

		Optional<Booking> findBookingById = bookingRepository.findById(newBooking.getBookingId());
		try {
			if (!findBookingById.isPresent()) {
				bookingRepository.save(newBooking);
				return new ResponseEntity<Booking>(newBooking, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"Booking with Booking Id: " + newBooking.getBookingId() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@Override
	public Booking updateBooking(Booking changedBooking) {
		Optional<Booking> findBookingById = bookingRepository.findById(changedBooking.getBookingId());
		if (findBookingById.isPresent()) {
			bookingRepository.save(changedBooking);
		} else
			throw new RecordNotFoundException(
					"Booking with Booking Id: " + changedBooking.getBookingId() + " not exists!!");
		return changedBooking;
	}


	@Override
	public String deleteBooking(BigInteger bookingId) {

		Optional<Booking> findBookingById = bookingRepository.findById(bookingId);
		if (findBookingById.isPresent()) {
			bookingRepository.deleteById(bookingId);
			return "Booking Deleted!!";
		} else
			throw new RecordNotFoundException("Booking not found for the entered BookingID");
	}

	@Override
	public Iterable<Booking> displayAllBooking() {

		return bookingRepository.findAll();
	}

	@Override
	public ResponseEntity<?> findBookingById(BigInteger bookingId) {
		Optional<Booking> findById = bookingRepository.findById(bookingId);
		try {
			if (findById.isPresent()) {
				Booking findBooking = findById.get();
				return new ResponseEntity<Booking>(findBooking, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + bookingId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}