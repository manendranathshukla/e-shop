package com.mycodeworks.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.model.Cart;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.request.AddItemRequest;
import com.mycodeworks.eshop.response.ApiResponse;
import com.mycodeworks.eshop.service.CartService;
import com.mycodeworks.eshop.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired private CartService cartService;
	@Autowired private UserService userService;
	
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findUserCart(user.getId());
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,   @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);

		cartService.addCartItem(user.getId(), req);
		ApiResponse res= new ApiResponse("item added to cart",true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	
	
}
