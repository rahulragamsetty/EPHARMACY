package com.epharmacy.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epharmacy.dto.CartDTO;
import com.epharmacy.dto.ProductDTO;

import com.epharmacy.exception.EPharmacyException;

import com.epharmacy.service.CustomerCartService;


@RequestMapping(value ="/cart")
@RestController
public class CartAPI {
	
	@Autowired
	private CustomerCartService cartService;
	
	@PostMapping(value ="/addcart")
	public ResponseEntity<String> addMedicinesToCart( @RequestBody CartDTO customerCartDTO)
			throws EPharmacyException {
		String response = cartService.addMedicinesToCart(customerCartDTO);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/getproducts/{email}")
	public ResponseEntity<Set<ProductDTO>> getMedicinesFromCart(@PathVariable String email)
			throws EPharmacyException {
		Set<ProductDTO> customerCartDTOs = cartService.getMedicinesFromCart(email);
		return new ResponseEntity<>(customerCartDTOs, HttpStatus.OK);

	}

	@PutMapping(value = "/modify/{email}/{productId}/{quantity}")
	public ResponseEntity<String> modifyQuantityOfMedicineInCart(@PathVariable String email,@PathVariable Integer productId , @PathVariable Integer quantity) throws EPharmacyException {
		String response = cartService.modifyQuantityOfMedicinesInCart(email, productId, quantity);
		return new ResponseEntity<String>(response, HttpStatus.OK);


	}

	@DeleteMapping(value = "/delete/{email}/{productId}")
	public ResponseEntity<String> deleteMedicineFromCart(@PathVariable String email,@PathVariable Integer productId) throws EPharmacyException {
		String response = cartService.deleteMedicineFromCart(email, productId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@DeleteMapping(value = "/deleteall/{email}")
	public ResponseEntity<String> deleteAllMedicinesFromCart(@PathVariable String email)
			throws EPharmacyException {
		String response = cartService.deleteAllMedicinesFromCart(email);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}