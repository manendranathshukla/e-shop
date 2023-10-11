package com.mycodeworks.eshop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Product;
import com.mycodeworks.eshop.model.Rating;
import com.mycodeworks.eshop.model.Review;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.repository.ProductRepository;
import com.mycodeworks.eshop.repository.ReviewRepository;
import com.mycodeworks.eshop.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {

	
	@Autowired private ReviewRepository reviewRepo;
	@Autowired private ProductService productService;
	@Autowired private ProductRepository productRepo;
	
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		
		Product product = productService.findProductById(req.getProductId());
		
		Review review= new Review();
		
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		
		return reviewRepo.save(review);
	}

	@Override
	public List<Review> getAllProductReview(Long prodcuctId) throws ProductException {
		return reviewRepo.getAllProductsReview(prodcuctId);
	}

}
