package com.epharmacy.dto;

import java.time.LocalDate;
import java.util.List;

import com.epharmacy.entity.Customer;


public class CustomerDTO {

	private Integer customerId;
	private String customerName;
	private String customerEmailId;
	private String contactNumber;
	private String password;
	private String gender;
	private String dateOfBirth;
	private List<CustomerAddressDTO> addressList;
	
	private PrimePlansDTO plan;
	private LocalDate planExpiryDate;
	

	public LocalDate getPlanExpiryDate() {
		return planExpiryDate;
	}

	public void setPlanExpiryDate(LocalDate planExpiryDate) {
		this.planExpiryDate = planExpiryDate;
	}

	public PrimePlansDTO getPlan() {
		return plan;
	}

	public void setPlan(PrimePlansDTO plan) {
		this.plan = plan;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CustomerAddressDTO> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<CustomerAddressDTO> addressList) {
		this.addressList = addressList;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public CustomerDTO entityToDto(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setCustomerName(customer.getCustomerName());
		customerDTO.setCustomerEmailId(customer.getCustomerEmailId());
		customerDTO.setContactNumber(customer.getContactNumber());
		customerDTO.setPassword(customer.getPassword());
		customerDTO.setGender(customer.getGender());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		return customerDTO;
	}

}
