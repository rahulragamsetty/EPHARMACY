package com.epharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDERED_MEDICINE")
public class OrderedMedicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderedMedicineId;
	private Integer medicineId;
	private Integer orderedQuantity;
	
	
	
	public Integer getOrderedMedicineId() {
		return orderedMedicineId;
	}
	public void setOrderedMedicineId(Integer orderedMedicineId) {
		this.orderedMedicineId = orderedMedicineId;
	}
	public Integer getOrderedQuantity() {
		return orderedQuantity;
	}
	public void setOrderedQuantity(Integer orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicineId == null) ? 0 : medicineId.hashCode());
		result = prime * result + ((orderedMedicineId == null) ? 0 : orderedMedicineId.hashCode());
		result = prime * result + ((orderedQuantity == null) ? 0 : orderedQuantity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedMedicine other = (OrderedMedicine) obj;
		if (medicineId == null) {
			if (other.medicineId != null)
				return false;
		} else if (!medicineId.equals(other.medicineId))
			return false;
		if (orderedMedicineId == null) {
			if (other.orderedMedicineId != null)
				return false;
		} else if (!orderedMedicineId.equals(other.orderedMedicineId))
			return false;
		if (orderedQuantity == null) {
			if (other.orderedQuantity != null)
				return false;
		} else if (!orderedQuantity.equals(other.orderedQuantity))
			return false;
		return true;
	}

	

}
