package com.epharmacy.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epharmacy.dto.CardDTO;
import com.epharmacy.dto.CustomerAddressDTO;

import com.epharmacy.dto.MedicineDTO;
import com.epharmacy.dto.OrderDTO;
import com.epharmacy.dto.OrderStatus;
import com.epharmacy.dto.OrderedMedicineDTO;
import com.epharmacy.dto.ProductDTO;
import com.epharmacy.entity.Orders;
import com.epharmacy.entity.OrderedMedicine;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.repository.OrderRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MedicineService medicineService;
	@Autowired 
	private CustomerCartService cartService;

	@Override
	public List<OrderDTO> viewOrders(String email) throws EPharmacyException {
		Iterable<Orders> optional = orderRepository.findByCustomerEmailId(email);
		List<OrderDTO> list = new ArrayList<>();
		for (Orders order : optional) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setPaymentId(order.getPaymentId());
			orderDTO.setMrpTotal(order.getMrpTotal());
			orderDTO.setDiscountPercent(order.getDiscountPercent());
			orderDTO.setDiscountedTotal(order.getDiscountedTotal());
			orderDTO.setDeliveryDate(order.getDeliveryDate());;
			orderDTO.setCustomerEmailId(order.getCustomerEmailId());
			orderDTO.setOrderStatus(order.getOrderStatus());;
			orderDTO.setOrderDate(order.getOrderDate());
			orderDTO.setCancelReason(order.getCancelReason());
			//set all details get address
			CustomerAddressDTO customerAddressDTO = customerService.getAddress(order.getDeliveryAddressId());
			orderDTO.setDeliveryAddress(customerAddressDTO);
			//get card details
			CardDTO cardDTO = paymentService.getCardDetails(order.getCardId());
			orderDTO.setCard(cardDTO);
			//convert the list
			List<OrderedMedicine> orderedMedicines = order.getOrderedMedicines();
			List<OrderedMedicineDTO> medicineDTOs = new ArrayList<>();
			for (OrderedMedicine orderedMedicine : orderedMedicines) {
				OrderedMedicineDTO orderedMedicineDTO = new OrderedMedicineDTO();
				orderedMedicineDTO.setOrderedQuantity(orderedMedicine.getOrderedQuantity());
				orderedMedicineDTO.setOrderedMedicineId(orderedMedicine.getOrderedMedicineId());
				//fetch the individual medicine details
				MedicineDTO medicineDTO =medicineService.getMedicineById(orderedMedicine.getMedicineId());
				orderedMedicineDTO.setMedicine(medicineDTO);
				medicineDTOs.add(orderedMedicineDTO);
			}
			orderDTO.setOrderedMedicines(medicineDTOs);
			list.add(orderDTO);
		}
		return list;
	}

	@Override
	public String placeOrder(OrderDTO orderDTO) throws EPharmacyException{
		Orders order = new Orders();
		order.setCustomerEmailId(orderDTO.getCustomerEmailId());
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus(OrderStatus.CONFIRMED);
		order.setDeliveryDate(LocalDateTime.now().plusDays(15));
		order.setDeliveryAddressId(orderDTO.getDeliveryAddress().getAddressId());
		order.setCardId(orderDTO.getCard().getCardId()); 
		String planName = customerService.viewCustomer(orderDTO.getCustomerEmailId()).
				getPlan().getPlanName();
		if(planName.equals("YEARLY")) order.setDiscountPercent(15.0);
		else if(planName.equals("QUARTERLY")) order.setDiscountPercent(10.0);
		else if(planName.equals("MONTHLY")) order.setDiscountPercent(5.0);
		else order.setDiscountPercent(0.0);
		double price =0.0;
		Set<ProductDTO> products = cartService.getMedicinesFromCart(orderDTO.getCustomerEmailId());
		cartService.deleteAllMedicinesFromCart(orderDTO.getCustomerEmailId());
		List<OrderedMedicineDTO> dtos = new ArrayList<>();
		for (ProductDTO dto : products) {
			OrderedMedicineDTO orDto = new OrderedMedicineDTO();
			orDto.setMedicine(dto.getMedicineDTO());
			orDto.setOrderedQuantity(dto.getQuantity());
			dtos.add(orDto);
		}
		List<OrderedMedicine> medicines = new ArrayList<>();
		
		for (OrderedMedicineDTO orderedMedicine : dtos) 
		{
		
			if(orderedMedicine.getMedicine().getQuantity()<orderedMedicine.getOrderedQuantity())
			{
				throw new EPharmacyException("STOCK_NOT_AVAILABLE");
			}
			
			OrderedMedicine orderedMedicine2 = new OrderedMedicine();
		
			orderedMedicine2.setMedicineId(orderedMedicine.getMedicine().getMedicineId());
			orderedMedicine2.setOrderedQuantity(orderedMedicine.getOrderedQuantity());
		
			medicineService.updateMedicineQuantityAfterOrder(orderedMedicine.getMedicine().getMedicineId(),
					orderedMedicine.getOrderedQuantity());
			medicines.add(orderedMedicine2);
			
			price = price + orderedMedicine.getOrderedQuantity()*orderedMedicine.getMedicine().getPrice();
		}
		order.setOrderedMedicines(medicines);
		
		order.setMrpTotal(price);
	
		order.setDiscountedTotal(price*(100 - order.getDiscountPercent())/100);
	
		Integer payment = paymentService.makePayment(orderDTO.getCard().getCardId(),order.getDiscountedTotal());
	
		order.setPaymentId(payment);
		
	
		orderRepository.save(order);
	
	
		return "order is placed with id: "+order.getOrderId();
	}

	@Override
	public String cancelOrder(Integer orderId, String reason) throws EPharmacyException {
	Optional<Orders> optional = orderRepository.findById(orderId);
	Orders order = optional.orElseThrow(()->new EPharmacyException("ORDER_ID_DOESNT_EXISTS"));
	order.setOrderStatus(OrderStatus.CANCELLED);
	order.setCancelReason(reason);
		return "Order is cancelled";
	}

	
}
