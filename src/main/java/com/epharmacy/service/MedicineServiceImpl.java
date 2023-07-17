package com.epharmacy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epharmacy.dto.MedicineDTO;
import com.epharmacy.entity.Medicine;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.repository.MedicineRepository;



@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private MedicineDTO mdto;

	@Override
	public List<MedicineDTO> getAllMedicines() throws EPharmacyException {
		Iterable<Medicine> iterable = medicineRepository.findAll(); // iterable of medicines
		//convert to dto from entities
		List<MedicineDTO> medicineDTOs = new ArrayList<>();
		for (Medicine medicine : iterable) {
			MedicineDTO mDto = mdto.entityToDto(medicine);
			medicineDTOs.add(mDto);
		}
		if(medicineDTOs.isEmpty()) return Collections.emptyList();
		return medicineDTOs;
	}

	@Override
	public List<MedicineDTO> getMedicinesByCategory(String category) throws EPharmacyException {
		Iterable<Medicine> iterable = medicineRepository.findByCategory(category); //repo - method creation
		List<MedicineDTO> medicineDTOs = new ArrayList<>();
		for (Medicine medicine : iterable) {
			MedicineDTO mDto = mdto.entityToDto(medicine);
			medicineDTOs.add(mDto);
		}
		if(medicineDTOs.isEmpty()) return Collections.emptyList();
		return medicineDTOs;
	}

	@Override
	public MedicineDTO getMedicineById(Integer medicineId) throws EPharmacyException {
		Optional<Medicine> optional = medicineRepository.findById(medicineId);
		Medicine medicine = optional.orElseThrow(()->new EPharmacyException("MEDICINE_DOESNT_EXISTS"));
		MedicineDTO medicineDTO = mdto.entityToDto(medicine);
		return medicineDTO;
	}

	@Override
	public String updateMedicineQuantityAfterOrder(Integer medicineId, Integer orderedQuantity)
			throws EPharmacyException {
		Optional<Medicine> optional = medicineRepository.findById(medicineId);
		Medicine medicine = optional.orElseThrow(()->new EPharmacyException("MEDICINE_DOESNT_EXISTS"));
		medicine.setQuantity(medicine.getQuantity()-orderedQuantity);
		return "Quantity updated successfully";
		
	}


}
