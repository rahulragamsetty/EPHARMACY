package com.epharmacy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.Cart;



@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>,PagingAndSortingRepository<Cart, Integer> {
	Optional<Cart> findByCustomerEmailId(String email);
}