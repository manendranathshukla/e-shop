package com.mycodeworks.eshop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Product;
import com.mycodeworks.eshop.model.Rating;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.repository.RatingRepository;
import com.mycodeworks.eshop.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService {

	
	@Autowired private RatingRepository ratingRepo;
	
	@Autowired private ProductService productService;
	
	
	@Override
	public Rating createRating(RatingRequest req,User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		
		Rating rating= new Rating();
		rating.setRating(req.getRating());
		rating.setProduct(product);
		rating.setCreatedAt(LocalDateTime.now());
		rating.setUser(user);
		return ratingRepo.save(rating);
	
	}

	@Override
	public List<Rating> getProductsRating(Long prductId) {
		return ratingRepo.getAllProductsRating(prductId);
	}

}
