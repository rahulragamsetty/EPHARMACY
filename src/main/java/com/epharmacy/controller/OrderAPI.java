package com.epharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epharmacy.dto.OrderDTO;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.service.OrderService;


@RestController
@RequestMapping(value = "order")
public class OrderAPI {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/allorders/{email}")
	public ResponseEntity<List<OrderDTO>> viewOrders(@PathVariable String email) throws EPharmacyException{
		List<OrderDTO> response = orderService.viewOrders(email);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@PostMapping(value = "/placeorder")
	public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) throws EPharmacyException{
		String response = orderService.placeOrder(orderDTO);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	@PutMapping(value = "/updateorder/{orderId}/{reason}")
	public ResponseEntity<String> cancelOrder(@PathVariable Integer orderId,@PathVariable String reason) throws EPharmacyException{
		String response = orderService.cancelOrder(orderId, reason);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}


}

