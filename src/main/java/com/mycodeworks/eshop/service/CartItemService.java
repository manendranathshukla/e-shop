package com.mycodeworks.eshop.service;

import com.mycodeworks.eshop.exception.CartItemException;
import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.model.Cart;
import com.mycodeworks.eshop.model.CartItem;
import com.mycodeworks.eshop.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id, CartItem cartItem) throws CartItemException, UserException;

	public CartItem isCartItemExist(Cart cart,Product product, String size, Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(long cartItemId) throws CartItemException;
	

}
