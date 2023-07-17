package com.epharmacy.entity;
import java.time.LocalDate;

import com.epharmacy.dto.MedicineDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "MEDICINE")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDICINE_ID")
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
	
	public Medicine dtoToEntity(MedicineDTO medicineDTO) {
		Medicine medicine = new Medicine();
		medicine.setMedicineId(medicineDTO.getMedicineId());
		medicine.setMedicineName(medicineDTO.getMedicineName());
		medicine.setManufacturer(medicineDTO.getManufacturer());
		medicine.setPrice(medicineDTO.getPrice());
		medicine.setDiscountPercent(medicineDTO.getDiscountPercent());
		medicine.setManufacturingDate(medicineDTO.getManufacturingDate());
		medicine.setExpiryDate(medicineDTO.getManufacturingDate());
		medicine.setCategory(medicineDTO.getCategory());
		medicine.setQuantity(medicineDTO.getQuantity());
		return medicine;
	}

}
