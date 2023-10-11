package com.mycodeworks.eshop.service;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Cart;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	
	public Cart findUserCart(Long userId);

}
