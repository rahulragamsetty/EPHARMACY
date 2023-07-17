package com.epharmacy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.OrderedMedicine;

@Repository
public interface OrderedMedicineRepository extends CrudRepository<OrderedMedicine, Integer>{


}
