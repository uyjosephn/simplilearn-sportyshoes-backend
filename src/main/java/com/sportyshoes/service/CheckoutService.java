package com.sportyshoes.service;

import com.sportyshoes.dto.Purchase;
import com.sportyshoes.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);
	
}
