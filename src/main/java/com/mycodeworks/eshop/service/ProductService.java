package com.mycodeworks.eshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Product;
import com.mycodeworks.eshop.request.CreateProductReqeust;


public interface ProductService {

	public Product createProduct(CreateProductReqeust req);
	
	public String deleteProduct(long productId) throws ProductException;

	public Product updateProduct(long productId,Product req) throws ProductException;
	
	public Product findProductById(long productId) throws ProductException;
	
	public List<Product> findProductByCategory(String category);

	public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes,
			Integer minPrice,Integer maxPrice, Integer minDiscount, String sort, String stock,
			Integer pageNumer,Integer pageSize);




}
