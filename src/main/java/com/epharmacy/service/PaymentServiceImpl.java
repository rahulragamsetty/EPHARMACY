package com.epharmacy.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epharmacy.dto.CardDTO;
import com.epharmacy.dto.PaymentDTO;
import com.epharmacy.entity.Card;
import com.epharmacy.entity.Payment;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.repository.CardRepository;
import com.epharmacy.repository.PaymentRepository;
import com.epharmacy.utility.HashingUtility;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private Card cardobj;
	@Autowired
	private CardDTO carddto;
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Integer makePayment(String cardId, Double amountToPay) throws EPharmacyException {
		Optional<Card> optional = cardRepository.findById(cardId);
		Card card = optional.orElseThrow(()->new EPharmacyException("CARD_DOESNT_EXISTS"));
		Payment payment = new Payment();
		payment.setCustomerEmailId(card.getCustomerEmailId());
		payment.setAmount(amountToPay);
		payment.setPaymentTime(LocalDateTime.now());
		payment.setCardId(card.getCardId());
		paymentRepository.save(payment);
		return payment.getPaymentId();
	}

	@Override
	public String addCard(CardDTO cardDTO) throws Exception {
		Optional<Card> optional = cardRepository.findById(cardDTO.getCardId());
		if(optional.isPresent()) {
			throw new EPharmacyException("CARD_ALREADY_EXISTS");
		}
		Card card = cardobj.dtoToEntity(cardDTO);
		card.setCvv(HashingUtility.getHashValue(cardDTO.getCvv()));
		cardRepository.save(card);
		return "Card is Added";
	}

	@Override
	public String deleteCard(String cardId) throws EPharmacyException {
		Optional<Card> optional = cardRepository.findById(cardId);
		Card card = optional.orElseThrow(()->new EPharmacyException("CARD_DOESNT_EXISTS"));
		cardRepository.delete(card);	
		return "Card is deleted";
	}

	@Override
	public List<CardDTO> viewCards(String email) throws EPharmacyException {
		Iterable<Card> iterable = cardRepository.findByCustomerEmailId(email);
		List<CardDTO> list = new ArrayList<>();
		for (Card card : iterable) {
			CardDTO cardDTO = carddto.entityToDto(card);
			list.add(cardDTO);
		}
		if(list.isEmpty()) return Collections.emptyList();
		return list;
	}

	@Override
	public PaymentDTO getPaymentDetails(Integer paymentId) throws EPharmacyException {
		Optional<Payment> optional = paymentRepository.findById(paymentId);
		Payment payment = optional.orElseThrow(()->new EPharmacyException("PAYMENT_DOESNT_EXISTS"));
		PaymentDTO paymentDTO = new PaymentDTO();
		CardDTO cardDTO = getCardDetails(payment.getCardId());
		paymentDTO.setCard(cardDTO);
		paymentDTO.setAmount(payment.getAmount());
		paymentDTO.setPaymentTime(payment.getPaymentTime());
		paymentDTO.setCustomerEmailId(payment.getCustomerEmailId());
		paymentDTO.setPaymentId(payment.getPaymentId());
		return paymentDTO;
	}

	@Override
	public CardDTO getCardDetails(String cardId) throws EPharmacyException {
		Optional<Card> optional = cardRepository.findById(cardId);
		Card card = optional.orElseThrow(()->new EPharmacyException("CARD_DOESNT_EXISTS"));
		CardDTO cardDTO = carddto.entityToDto(card);
		return cardDTO;
	}
	


}
