package com.epharmacy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card,String>{
	Iterable<Card> findByCustomerEmailId(String customerEmailId);
}
