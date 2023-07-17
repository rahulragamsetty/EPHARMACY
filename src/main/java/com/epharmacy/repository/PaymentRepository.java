package com.epharmacy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
