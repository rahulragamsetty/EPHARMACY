package com.epharmacy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	Optional<Customer> findByCustomerEmailId(String customerEmailId);
	
	
}
