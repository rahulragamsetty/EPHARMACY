package com.epharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epharmacy.dto.MedicineDTO;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.service.MedicineService;
import com.epharmacy.service.MedicineServiceImpl;


@RestController
@RequestMapping(value= "medicine")
@Validated 
@CrossOrigin
public class MedicineAPI {
	
	@Autowired
	private MedicineService medicineService;
	
	@GetMapping(value = "/getall")
	public ResponseEntity<List<MedicineDTO>> getAllMedicines() throws EPharmacyException{
		List<MedicineDTO> list = medicineService.getAllMedicines();
		return new 	ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping(value = "/getbycategory/{category}")
	public ResponseEntity<List<MedicineDTO>> getMedicinesByCategory(@PathVariable String category)throws EPharmacyException{
		List<MedicineDTO> list = medicineService.getMedicinesByCategory(category);
		return new 	ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping(value = "/getbyid/{medicineId}")
	public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable Integer medicineId) throws EPharmacyException{
		MedicineDTO medicineDTO = medicineService.getMedicineById(medicineId);
		return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
	}
	@PutMapping(value = "/updatequantity/{medicineId}/{orderedQuantity}" )
	public ResponseEntity<String> updateMedicineQuantityAfterOrder(@PathVariable Integer medicineId,@PathVariable Integer orderedQuantity) throws EPharmacyException{
		String response = medicineService.updateMedicineQuantityAfterOrder(medicineId, orderedQuantity);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
