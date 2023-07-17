package com.epharmacy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine,Integer>{
	Iterable<Medicine> findByCategory(String category);
	
}
