package com.epharmacy.service;


import java.util.List;

import com.epharmacy.dto.CardDTO;
import com.epharmacy.dto.PaymentDTO;
import com.epharmacy.exception.EPharmacyException;


public interface PaymentService {
	public Integer makePayment(String cardId,Double amountToPay)
			throws EPharmacyException;

	public String addCard(CardDTO cardDTO) throws Exception;
	public String deleteCard(String cardId) throws EPharmacyException;
	public List<CardDTO> viewCards(String email) throws EPharmacyException;
	PaymentDTO getPaymentDetails(Integer paymentId) throws EPharmacyException;
	CardDTO getCardDetails(String cardId) throws EPharmacyException;
}
