package com.epharmacy.entity;
import org.springframework.stereotype.Component;
import com.epharmacy.dto.CardDTO;
import com.epharmacy.dto.CardType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
@Component
public class Card {
	@Id
	private String cardId;
	private String nameOnCard;
	private String cvv;
	private String expiryDate;
	@Enumerated(EnumType.STRING)
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
	public Card dtoToEntity(CardDTO cardDTO) {
		Card card = new Card();
		card.setCardId(cardDTO.getCardId());
		card.setExpiryDate(cardDTO.getExpiryDate());
		card.setNameOnCard(cardDTO.getNameOnCard());
		card.setCvv(cardDTO.getCvv());
		card.setCardType(cardDTO.getCardType());
		card.setCustomerEmailId(cardDTO.getCustomerEmailId());
		return card;
	}
}
