package com.epharmacy.service;

import java.util.List;
import java.util.Set;

import com.epharmacy.dto.CartDTO;
import com.epharmacy.dto.ProductDTO;
import com.epharmacy.exception.EPharmacyException;




public interface CustomerCartService {

	String addMedicinesToCart(CartDTO cart) throws EPharmacyException;

	Set<ProductDTO> getMedicinesFromCart(String email) throws EPharmacyException;

	String modifyQuantityOfMedicinesInCart(String email, Integer productId , Integer quantity)
			throws EPharmacyException;

	String deleteMedicineFromCart(String email, Integer productId) throws EPharmacyException;

	String deleteAllMedicinesFromCart(String email) throws EPharmacyException;

}