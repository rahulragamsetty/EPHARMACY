package com.epharmacy.dto;

import com.epharmacy.entity.CustomerAddress;

 
public class CustomerAddressDTO {
	
	private Integer addressId;
	private String addressName;
	private String addressLine1;
	private String addressLine2;
	private String area;
	private String city;
	private String state;
	private String pincode;
	


	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public CustomerAddressDTO entityToDto(CustomerAddress customerAddress) {
		CustomerAddressDTO caDTO = new CustomerAddressDTO();
		caDTO.setAddressId(customerAddress.getAddressId());
		caDTO.setAddressName(customerAddress.getAddressName());
		caDTO.setAddressLine1(customerAddress.getAddressLine1());
		caDTO.setAddressLine2(customerAddress.getAddressLine2());
		caDTO.setArea(customerAddress.getArea());
		caDTO.setCity(customerAddress.getCity());
		caDTO.setState(customerAddress.getState());
		caDTO.setPincode(customerAddress.getPincode());
		return caDTO;
	}

}
