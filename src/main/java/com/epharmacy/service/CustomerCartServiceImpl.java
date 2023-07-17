package com.epharmacy.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epharmacy.dto.CartDTO;
import com.epharmacy.dto.MedicineDTO;
import com.epharmacy.dto.ProductDTO;
import com.epharmacy.entity.Cart;
import com.epharmacy.entity.Product;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.repository.CartRepository;
import com.epharmacy.repository.ProductRepository;



@Service
@Transactional
public class CustomerCartServiceImpl implements CustomerCartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MedicineService medicineService;

	@Override
	public String addMedicinesToCart(CartDTO cart)
			throws EPharmacyException {
		Set<Product> products = new HashSet<>();
		for(ProductDTO productDTO : cart.getProducts()) {
			Product product = new Product();
			product.setMedicineId(productDTO.getMedicineDTO().getMedicineId());
			product.setQuantity(productDTO.getQuantity());
			Integer id =productRepository.save(product).getProductId();
			product.setProductId(id);
			products.add(product);
		}
	
		Optional<Cart> optional = cartRepository.findByCustomerEmailId(cart.getCustomerEmailId());
		if(optional.isEmpty()) {
			Cart newCart = new Cart();
			newCart.setCustomerEmailId(cart.getCustomerEmailId());
			newCart.setProducts(products);
			cartRepository.save(newCart);
		}
		else {
			Cart existing = optional.get();
			for(Product toadd: products) {
				Boolean alreadyPresent = false;
				for(Product existingProducts: existing.getProducts()) {
					if(existingProducts.getMedicineId().equals(toadd.getMedicineId())) {
						{
							
							existingProducts.setQuantity(toadd.getQuantity()+existingProducts.getQuantity());
							productRepository.delete(toadd);
							alreadyPresent = true;
						}
					}
				}
				if(alreadyPresent==false) {
					existing.getProducts().add(toadd);
				}
			}
		}
		return "Products are successfully added to the cart ";
	}

	@Override
	public Set<ProductDTO> getMedicinesFromCart(String email) throws EPharmacyException {
		Optional<Cart> optional = cartRepository.findByCustomerEmailId(email);
		Cart cart = optional.orElseThrow(()->new EPharmacyException("NO_CART_AVAILABLE"));
		if(cart.getProducts().isEmpty()) {
			throw new EPharmacyException("NO_PRODUCTS_ADDED TO THE CART");
		}
		Set<ProductDTO> productDTOs = new HashSet<>();
		Set<Product> products = cart.getProducts();
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductId(product.getProductId());
			productDTO.setQuantity(product.getQuantity());
			MedicineDTO medicineDTO = medicineService.getMedicineById(product.getMedicineId());
			productDTO.setMedicineDTO(medicineDTO);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	@Override
	public String modifyQuantityOfMedicinesInCart(String email, Integer productId , Integer quantity)
			throws EPharmacyException {
		Optional<Cart> optional = cartRepository.findByCustomerEmailId(email);
		Cart cart = optional.orElseThrow(()->new EPharmacyException("NO_CART_AVAILABLE"));
		if(cart.getProducts().isEmpty()) {
			throw new EPharmacyException("NO_PRODUCTS_ADDED TO THE CART");
		}
		Product selectedProduct = null;
		for (Product product : cart.getProducts()) {
			if(product.getProductId().equals(productId)) {
				selectedProduct = product;
				break;
			}
		}
		if(selectedProduct == null) {
			throw new EPharmacyException("THIS_PRODUCT_DOESNT_EXISTS");
		}
		selectedProduct.setQuantity(quantity);
		return "Quantity is updated";
	}

	@Override
	public String deleteMedicineFromCart(String email, Integer productId) throws EPharmacyException {
		Optional<Cart> optional = cartRepository.findByCustomerEmailId(email);
		Cart cart = optional.orElseThrow(()->new EPharmacyException("NO_CART_AVAILABLE"));
		if(cart.getProducts().isEmpty()) {
			throw new EPharmacyException("NO_PRODUCTS_ADDED TO THE CART");
		}
		Product selectedProduct = null;
		for (Product product : cart.getProducts()) {
			if(product.getProductId().equals(productId)) {
				selectedProduct = product;
				break;
			}
		}
		cart.getProducts().remove(selectedProduct);
		productRepository.delete(selectedProduct);
		return "Product is deleted from the cart";
	}

	@Override
	public String deleteAllMedicinesFromCart(String email) throws EPharmacyException {
		Optional<Cart> optional = cartRepository.findByCustomerEmailId(email);
		Cart cart = optional.orElseThrow(()->new EPharmacyException("NO_CART_AVAILABLE"));
		if(cart.getProducts().isEmpty()) {
			throw new EPharmacyException("NO_PRODUCTS_ADDED TO THE CART");
		}
		List<Integer> productIds = new ArrayList<>();
		//this is iterable if needed convert into dto - set then proceed with traditional way
		cart.getProducts().parallelStream().forEach(product ->{
			productIds.add(product.getProductId());
			cart.getProducts().remove(product);
		});
		for (Integer id: productIds) {
			productRepository.deleteById(id);
		} 
		
		
		return "All the products in the cart are deleted";
	}
	
	
}