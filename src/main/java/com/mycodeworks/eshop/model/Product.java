package com.mycodeworks.eshop.model;

import java.util.HashSet;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="discounted_price")
	private int discountedPrice;
	
	@Column(name="discount_percent")
	private int discountedPercent;
	
	@Column(name="quantity")
	private int quantity;
	
	private int price;
	
	private String brand;
	private String color;
	
	@Embedded
	@ElementCollection
	@Column(name="sizes")
	private Set<Size> sizes= new HashSet<>();
	
	
	@Column(name="image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Rating> ratings= new ArrayList<>();
	
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();
	
	
	@Column(name="num_ratings")
	private int numRatings;
	
	
	@ManyToOne()
	@JoinColumn(name="category_id")
	private Category category;
	
	private LocalDateTime createdAt;
	
	
	
	
	
}
