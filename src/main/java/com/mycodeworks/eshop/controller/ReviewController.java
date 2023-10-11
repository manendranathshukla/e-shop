package com.mycodeworks.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.model.Rating;
import com.mycodeworks.eshop.model.Review;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.request.ReviewRequest;
import com.mycodeworks.eshop.service.ReviewService;
import com.mycodeworks.eshop.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

   @Autowired private UserService userService;
	
	@Autowired private ReviewService reviewService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Review> createRating(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException,ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		
		Review review = reviewService.createReview(req,user);
		return new ResponseEntity<Review>(review,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductReview(@PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws UserException,ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Review> reviews = reviewService.getAllProductReview(productId);
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.ACCEPTED);
	}
}
