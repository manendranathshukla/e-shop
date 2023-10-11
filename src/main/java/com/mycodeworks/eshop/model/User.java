package com.mycodeworks.eshop.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String role;
	private String mobile;

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Address> address= new ArrayList<>();
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment_information",joinColumns=@JoinColumn(name="user_id"))
	private List<PaymentInformation> paymentInformation= new ArrayList<>();
	
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Rating> ratings= new ArrayList<>();
	
	@OneToMany(mappedBy="user",  cascade=CascadeType.ALL)
	private List<Review> reviews= new ArrayList<>();
	
	private LocalDateTime  createdAt;

}
