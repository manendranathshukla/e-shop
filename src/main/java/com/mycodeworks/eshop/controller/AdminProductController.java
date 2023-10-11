package com.mycodeworks.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Product;
import com.mycodeworks.eshop.request.CreateProductReqeust;
import com.mycodeworks.eshop.response.ApiResponse;
import com.mycodeworks.eshop.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
	
	@Autowired private ProductService productService;
	
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductReqeust req){
		Product  product= productService.createProduct(req);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
		productService.deleteProduct(productId);
		ApiResponse res = new ApiResponse("product deleted successfully",true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProduct(){
		List<Product> products= productService.findAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product req, @PathVariable Long productId) throws ProductException{
		
		Product product= productService.updateProduct(productId, req);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductReqeust[] req){
		for(CreateProductReqeust p : req) {
			productService.createProduct(p);
		}
		ApiResponse res = new ApiResponse("products created successfully",true);
	
		return new ResponseEntity<ApiResponse>(res,HttpStatus.CREATED);
	
	}
	
	
	

}
