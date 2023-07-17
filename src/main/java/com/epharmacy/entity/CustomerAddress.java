package com.epharmacy.entity;

import com.epharmacy.dto.CustomerAddressDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="customer_address")
public class CustomerAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	public CustomerAddress dtoToEntity(CustomerAddressDTO customerAddressDto) {
		CustomerAddress address = new CustomerAddress();
		address.setAddressId(customerAddressDto.getAddressId());
		address.setAddressName(customerAddressDto.getAddressName());
		address.setAddressLine1(customerAddressDto.getAddressLine1());
		address.setAddressLine2(customerAddressDto.getAddressLine2());
		address.setArea(customerAddressDto.getArea());
		address.setCity(customerAddressDto.getCity());
		address.setState(customerAddressDto.getState());
		address.setPincode(customerAddressDto.getPincode());
		return address;
	}
}
