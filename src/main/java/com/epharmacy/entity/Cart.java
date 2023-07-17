package com.epharmacy.entity;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

	@Id
	@Column(name = "CART_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	private String customerEmailId;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartId")
	private Set<Product> products;
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	

}