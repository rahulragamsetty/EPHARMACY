package com.epharmacy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epharmacy.entity.PrimePlans;

@Repository
public interface PrimePlansRepository extends CrudRepository<PrimePlans, Integer>{
	Optional<PrimePlans> findByPlanName(String planName);

	
}
