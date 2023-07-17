package com.epharmacy.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epharmacy.dto.CustomerAddressDTO;
import com.epharmacy.dto.CustomerDTO;
import com.epharmacy.dto.PrimePlansDTO;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.service.CustomerService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@CrossOrigin
@RestController
@RequestMapping(value = "/customer")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = "/authenticate/{emailId}/{password}")
	public ResponseEntity<String> authenticateCustomer(@PathVariable String emailId,@PathVariable String password) throws Exception{
		String response = customerService.authenticateCustomer(emailId, password);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value = "/register")
	public ResponseEntity<String> registerNewCustomer( @RequestBody CustomerDTO customerDTO) throws Exception{
		String response = customerService.registerNewCustomer(customerDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping(value = "/getcustomer/{email}")
	public ResponseEntity<CustomerDTO> viewCustomer(@PathVariable String email) throws EPharmacyException{
		CustomerDTO response = customerService.viewCustomer(email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping(value = "/upgrade")
	public ResponseEntity<LocalDate> upgradeCustomerToPrime( @RequestBody CustomerDTO customerDTO) throws EPharmacyException{
		LocalDate response = customerService.upgradeCustomerToPrime(customerDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping(value = "/getplan/{email}")
	public ResponseEntity<PrimePlansDTO>  getPlanById(@PathVariable String email)throws EPharmacyException{
		PrimePlansDTO response = customerService.getPlan(email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@DeleteMapping(value = "/delete/{addressId}")
	public ResponseEntity<String>  deleteAddress(@PathVariable Integer addressId) throws EPharmacyException{
		String response = customerService.deleteAddress(addressId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping(value = "/addaddress/{email}")
	public ResponseEntity<String>  addCustomerAddress( @RequestBody CustomerAddressDTO caDTO,@PathVariable String email) throws EPharmacyException{
		String response = customerService.addCustomerAddress(caDTO, email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping(value = "/addresses/{email}")
	public ResponseEntity<List<CustomerAddressDTO>> viewAllAddress(@PathVariable String email) throws EPharmacyException{
		List<CustomerAddressDTO> response = customerService.viewAllAddress(email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
