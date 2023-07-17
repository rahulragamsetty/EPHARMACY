package com.epharmacy.dto;

import org.springframework.stereotype.Component;

import com.epharmacy.entity.Product;

@Component
public class ProductDTO {
	
	private Integer productId;
	private MedicineDTO medicineDTO;
	private Integer quantity;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public MedicineDTO getMedicineDTO() {
		return medicineDTO;
	}
	public void setMedicineDTO(MedicineDTO medicineDTO) {
		this.medicineDTO = medicineDTO;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public ProductDTO entityToDto(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setQuantity(product.getQuantity());
		return productDTO;
	}
}
