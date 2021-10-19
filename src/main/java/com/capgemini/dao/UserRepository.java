package com.capgemini.dao;
import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import com.capgemini.entities.Users;

public interface UserRepository extends CrudRepository<Users, BigInteger>{

}