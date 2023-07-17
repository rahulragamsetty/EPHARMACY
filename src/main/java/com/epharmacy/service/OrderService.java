package com.epharmacy.service;

import java.util.List;

import com.epharmacy.dto.OrderDTO;
import com.epharmacy.exception.EPharmacyException;



public interface OrderService {
	List<OrderDTO> viewOrders(String email) throws EPharmacyException;
	public String placeOrder(OrderDTO orderDTO) throws EPharmacyException;
	public String cancelOrder(Integer orderId,String reason) throws EPharmacyException;
}
