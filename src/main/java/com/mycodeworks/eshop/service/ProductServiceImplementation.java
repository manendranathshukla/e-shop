package com.mycodeworks.eshop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.ProductException;
import com.mycodeworks.eshop.model.Category;
import com.mycodeworks.eshop.model.Product;
import com.mycodeworks.eshop.repository.CategoryRepository;
import com.mycodeworks.eshop.repository.ProductRepository;
import com.mycodeworks.eshop.request.CreateProductReqeust;

@Service
public class ProductServiceImplementation implements ProductService {

	
	@Autowired private ProductRepository productRepo;
	@Autowired private CategoryRepository categoryRepo;
	@Autowired private UserServiceImplementation userService;
	
	
	
	@Override
	public Product createProduct(CreateProductReqeust req) {
		// TODO Auto-generated method stub
		Category topLevel= categoryRepo.findByName(req.getTopLevelCategory());

		if(topLevel == null) {
			Category topLevelCategory= new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			
			topLevel=categoryRepo.save(topLevelCategory);
		}
		Category secondLevel= categoryRepo.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());

		if(secondLevel == null) {
			Category secondLevelCategory= new Category();
			secondLevelCategory.setName(req.getSecondLevelCategory());
			secondLevelCategory.setLevel(2);
			secondLevelCategory.setParentCategory(topLevel);
			secondLevel=categoryRepo.save(secondLevelCategory);
		}
		Category thirdLevel= categoryRepo.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());

		if(thirdLevel == null) {
			Category thirdLevelCategory= new Category();
			thirdLevelCategory.setName(req.getThirdLevelCategory());
			thirdLevelCategory.setLevel(2);
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevel=categoryRepo.save(thirdLevelCategory);
		}
		
		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setBrand(req.getBrand());
		product.setDescription(req.getDescription());
		product.setDiscountedPercent(req.getDiscountPercent());
		product.setImageUrl(req.getImageUrl());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		Product savedProduct = productRepo.save(product);		
		return savedProduct;
	}

	@Override
	public String deleteProduct(long productId) throws ProductException {
		
		Product product= findProductById(productId);
		product.getSizes().clear();
		productRepo.delete(product);
		return "Product deleted Successfully";
	}

	@Override
	public Product updateProduct(long productId, Product req) throws ProductException {
		
		Product product= findProductById(productId);
		if(req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}
		return productRepo.save(product);
	}

	@Override
	public Product findProductById(long productId) throws ProductException {
		Optional<Product> p = productRepo.findById(productId);
		if(p.isPresent()) {
			return p.get();
		}else {
			throw new ProductException("Product not found with id-"+productId);
		}
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		
		Pageable pageble = PageRequest.of(pageNumber, pageSize);
		List<Product> products= productRepo.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
	
		if(!colors.isEmpty()) {
			products= products.stream().filter(p-> colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
	
		if(stock != null) {
			if(stock.equals("in_stock")){
				products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
				
			}
			else if(stock.equals("out_of_stock")) {
				products= products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
			}
			
		}
		
		int startIndex=(int)pageble.getOffset();
		int endIndex=Math.min(startIndex+pageble.getPageSize(),products.size());
		
		List<Product> pageContent=products.subList(startIndex, endIndex);
	
		Page<Product> filteredProducts= new PageImpl<>(pageContent,pageble,products.size());
	
		return filteredProducts;
	}

}
