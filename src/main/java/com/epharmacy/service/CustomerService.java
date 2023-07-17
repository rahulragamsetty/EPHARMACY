package com.epharmacy.service;
import java.time.LocalDate;
import java.util.List;

import com.epharmacy.dto.CustomerAddressDTO;
import com.epharmacy.dto.CustomerDTO;
import com.epharmacy.dto.PrimePlansDTO;
import com.epharmacy.exception.EPharmacyException;


public interface CustomerService {

	String authenticateCustomer(String emailId, String password) throws Exception;

	String registerNewCustomer(CustomerDTO customerDTO) throws Exception;

	CustomerDTO viewCustomer(String email) throws EPharmacyException;

	LocalDate upgradeCustomerToPrime(CustomerDTO customerDTO) throws EPharmacyException;
	
	PrimePlansDTO getPlan(String email)throws EPharmacyException;
	
	String deleteAddress(Integer addressId) throws EPharmacyException;
	
	String addCustomerAddress(CustomerAddressDTO caDTO, String email) throws EPharmacyException;
	
	List<CustomerAddressDTO> viewAllAddress(String email) throws EPharmacyException;
	
	CustomerAddressDTO getAddress(Integer deliveryId) throws EPharmacyException;
	

}
