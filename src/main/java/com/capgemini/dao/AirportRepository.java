package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.entities.Airport;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {

}

