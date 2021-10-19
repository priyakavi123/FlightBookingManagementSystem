package com.capgemini.dao;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, BigInteger> {

}
