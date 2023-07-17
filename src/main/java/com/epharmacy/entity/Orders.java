package com.epharmacy.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.epharmacy.dto.OrderStatus;

import jakarta.persistence.*;


@Entity
@Table(name = "ORDERS")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Integer paymentId;
	private Double mrpTotal;
	private Double discountPercent;
	private Double discountedTotal;
	private LocalDateTime deliveryDate;
	private String customerEmailId;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private LocalDateTime orderDate;
	private String cancelReason;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderedMedicine> orderedMedicines;
	
	private Integer deliveryAddressId;
	private String cardId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public List<OrderedMedicine> getOrderedMedicines() {
		return orderedMedicines;
	}
	public void setOrderedMedicines(List<OrderedMedicine> orderedMedicines) {
		this.orderedMedicines = orderedMedicines;
	}
	public Integer getDeliveryAddressId() {
		return deliveryAddressId;
	}
	public void setDeliveryAddressId(Integer deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public String getCardId() {
		return cardId;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public Double getMrpTotal() {
		return mrpTotal;
	}
	public void setMrpTotal(Double mrpTotal) {
		this.mrpTotal = mrpTotal;
	}
	public Double getDiscountedTotal() {
		return discountedTotal;
	}
	public void setDiscountedTotal(Double discountedTotal) {
		this.discountedTotal = discountedTotal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancelReason == null) ? 0 : cancelReason.hashCode());
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((customerEmailId == null) ? 0 : customerEmailId.hashCode());
		result = prime * result + ((deliveryAddressId == null) ? 0 : deliveryAddressId.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((discountPercent == null) ? 0 : discountPercent.hashCode());
		result = prime * result + ((discountedTotal == null) ? 0 : discountedTotal.hashCode());
		result = prime * result + ((mrpTotal == null) ? 0 : mrpTotal.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((orderedMedicines == null) ? 0 : orderedMedicines.hashCode());
		result = prime * result + ((paymentId == null) ? 0 : paymentId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (cancelReason == null) {
			if (other.cancelReason != null)
				return false;
		} else if (!cancelReason.equals(other.cancelReason))
			return false;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (customerEmailId == null) {
			if (other.customerEmailId != null)
				return false;
		} else if (!customerEmailId.equals(other.customerEmailId))
			return false;
		if (deliveryAddressId == null) {
			if (other.deliveryAddressId != null)
				return false;
		} else if (!deliveryAddressId.equals(other.deliveryAddressId))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (discountPercent == null) {
			if (other.discountPercent != null)
				return false;
		} else if (!discountPercent.equals(other.discountPercent))
			return false;
		if (discountedTotal == null) {
			if (other.discountedTotal != null)
				return false;
		} else if (!discountedTotal.equals(other.discountedTotal))
			return false;
		if (mrpTotal == null) {
			if (other.mrpTotal != null)
				return false;
		} else if (!mrpTotal.equals(other.mrpTotal))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (orderedMedicines == null) {
			if (other.orderedMedicines != null)
				return false;
		} else if (!orderedMedicines.equals(other.orderedMedicines))
			return false;
		if (paymentId == null) {
			if (other.paymentId != null)
				return false;
		} else if (!paymentId.equals(other.paymentId))
			return false;
		return true;
	}

	
	

}
