package com.mycodeworks.eshop.service;

import java.util.List;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Review;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException ;
	
	public List<Review> getAllProductReview(Long prodcuctId) throws ProductException;
}
