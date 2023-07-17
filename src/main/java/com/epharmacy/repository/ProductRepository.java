package com.epharmacy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
