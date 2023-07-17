package com.epharmacy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{
	Iterable<Orders> findByCustomerEmailId(String customerEmailId);
	
}
