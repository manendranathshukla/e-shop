package com.mycodeworks.eshop.service;

import java.util.List;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Rating;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.request.RatingRequest;


public interface RatingService {

	
	public Rating createRating(RatingRequest req,User user) throws ProductException;

	public List<Rating> getProductsRating(Long prductId);


}
