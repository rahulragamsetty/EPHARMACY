package com.epharmacy.service;

import java.util.List;

import com.epharmacy.dto.MedicineDTO;
import com.epharmacy.exception.EPharmacyException;



public interface MedicineService {
	
	List<MedicineDTO> getAllMedicines() throws EPharmacyException;   // findAll
	
	List<MedicineDTO> getMedicinesByCategory(String category)throws EPharmacyException; //findByCategory
	
	MedicineDTO getMedicineById(Integer medicineId) throws EPharmacyException; //findById
	
	String updateMedicineQuantityAfterOrder(Integer medicineId, Integer orderedQuantity) throws EPharmacyException;
	         
		//  findById
}
