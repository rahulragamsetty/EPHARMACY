package com.epharmacy.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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

import com.epharmacy.dto.CardDTO;
import com.epharmacy.dto.PaymentDTO;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.service.PaymentService;


@RestController
@RequestMapping(value = "/payment")
public class PaymentAPI {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping(value = "/addcard")
	public ResponseEntity<String> addCard(@RequestBody CardDTO cardDTO) throws Exception{
		String response = paymentService.addCard(cardDTO);
		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}
	@DeleteMapping(value = "/delete/{cardId}")
	public ResponseEntity<String> deleteCard(@PathVariable String cardId) throws EPharmacyException{
		String response = paymentService.deleteCard(cardId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	@GetMapping(value = "/getallcards/{email}")
	public ResponseEntity<List<CardDTO>> viewCards(@PathVariable String email) throws EPharmacyException{
		List<CardDTO> response = paymentService.viewCards(email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping(value = "/getpayment/{paymentId}")
	public ResponseEntity<PaymentDTO> getPaymentDetails(@PathVariable Integer paymentId) throws EPharmacyException{
		PaymentDTO response = paymentService.getPaymentDetails(paymentId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping(value = "/getcard/{cardId}")
	public ResponseEntity<CardDTO> getCardDetails(@PathVariable String cardId) throws EPharmacyException{
		CardDTO response = paymentService.getCardDetails(cardId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping(value = "/makepayment/{cardId}/{amountToPay}")
	public ResponseEntity<Integer> makePayment( @PathVariable String cardId,@PathVariable Double amountToPay)
			throws EPharmacyException, NoSuchAlgorithmException{
		Integer response = paymentService.makePayment(cardId, amountToPay);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
