package com.epharmacy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, Integer> {
	
}
