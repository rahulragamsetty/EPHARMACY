package com.epharmacy.dto;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.epharmacy.entity.Medicine;



@Component
public class MedicineDTO {
	
	private Integer medicineId;
	
	private String medicineName;
	
	private String manufacturer;
	
	private Integer price;
	private Integer discountPercent;
	
	private LocalDate manufacturingDate;
	
	private LocalDate expiryDate;
	
	private String category;
	
	private Integer quantity;
	
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

	public MedicineDTO entityToDto(Medicine medicine) {
		MedicineDTO medicineDTO = new MedicineDTO();
		medicineDTO.setMedicineId(medicine.getMedicineId());
		medicineDTO.setMedicineName(medicine.getMedicineName());
		medicineDTO.setManufacturer(medicine.getManufacturer());
		medicineDTO.setPrice(medicine.getPrice());
		medicineDTO.setDiscountPercent(medicine.getDiscountPercent());
		medicineDTO.setManufacturingDate(medicine.getManufacturingDate());
		medicineDTO.setExpiryDate(medicine.getManufacturingDate());
		medicineDTO.setCategory(medicine.getCategory());
		medicineDTO.setQuantity(medicine.getQuantity());
		return medicineDTO;
	}
	
	

}
