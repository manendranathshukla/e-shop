package com.mycodeworks.eshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.CartItemException;
import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.model.Cart;
import com.mycodeworks.eshop.model.CartItem;
import com.mycodeworks.eshop.model.Product;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.repository.CartItemRepository;
import com.mycodeworks.eshop.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {

	@Autowired private CartItemRepository cartItemRepo;
	@Autowired private UserService userService;
	
	@Autowired private CartRepository cartRepository;
	
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
		CartItem createdCartItem = cartItemRepo.save(cartItem);
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		 
		CartItem item= findCartItemById(id);
		User user = userService.findUserById(userId);
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()* item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
			
		}
		return cartItemRepo.save(item);
		
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		CartItem cartItem = cartItemRepo.isCartItemExist(cart, product, size, userId);
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem = findCartItemById(cartItemId);
		
		User user= userService.findUserById(cartItem.getUserId());
		
		User reqUser = userService.findUserById(userId);
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepo.deleteById(cartItemId);
		}
		else {
			throw new UserException("you can't remove another users item");
		}
	}

	@Override
	public CartItem findCartItemById(long cartItemId) throws CartItemException {
		Optional<CartItem> opt= cartItemRepo.findById(cartItemId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("cartitem not found with id : "+ cartItemId); 
	}

}
