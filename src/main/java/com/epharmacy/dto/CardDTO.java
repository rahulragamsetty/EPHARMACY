package com.epharmacy.dto;



import org.springframework.stereotype.Component;

import com.epharmacy.entity.Card;

@Component
public class CardDTO {
	private String cardId;
	private String nameOnCard;
	private String cvv;
	private String expiryDate;
	private CardType cardType;
	private String customerEmailId;
	
	
	
	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	public CardDTO entityToDto(Card card) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setCardId(card.getCardId());
		cardDTO.setExpiryDate(card.getExpiryDate());
		cardDTO.setNameOnCard(card.getNameOnCard());
		cardDTO.setCvv(card.getCvv());
		cardDTO.setCardType(card.getCardType());
		cardDTO.setCustomerEmailId(card.getCustomerEmailId());
		return cardDTO;
	}
}
