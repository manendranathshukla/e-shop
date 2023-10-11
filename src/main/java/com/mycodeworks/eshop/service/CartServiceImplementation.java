package com.mycodeworks.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Cart;
import com.mycodeworks.eshop.model.CartItem;
import com.mycodeworks.eshop.model.Product;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.repository.CartRepository;
import com.mycodeworks.eshop.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{

	@Autowired private CartRepository cartRepo;
	@Autowired private CartItemService cartItemService;
	@Autowired private ProductService productService;
	
	
	@Override
	public Cart createCart(User user) {
		Cart cart= new Cart();
		cart.setUser(user);
		return cartRepo.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart = cartRepo.findUserById(userId);
		Product product = productService.findProductById(req.getProductId());
		
		CartItem isPresent= cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price= req.getQuantity() * product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem= cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			
		}
		return "Item Add To Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartRepo.findUserById(userId);
		
		int totalPrice=0;
		int totalDisountedPrice=0;
		int totalItem=0;
		
		for(CartItem c : cart.getCartItems()) {
			totalPrice= totalPrice+c.getPrice();
			totalDisountedPrice= totalDisountedPrice+c.getDiscountedPrice();
			totalItem= totalItem+c.getQuantity();
			
		}
		cart.setTotalDiscountePrice(totalDisountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDisount(totalPrice-totalDisountedPrice);
		return cartRepo.save(cart);
	}

}
