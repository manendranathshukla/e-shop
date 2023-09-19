package com.mycodeworks.eshop.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.response.AuthResponse;
import com.mycodeworks.eshop.jwt.JwtUtils;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.repository.UserRepository;
import com.mycodeworks.eshop.request.LoginRequest;
import com.mycodeworks.eshop.service.CustomUserServiceImplementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    AuthResponse authResp= new AuthResponse(jwt,"Signin Success");
	return new ResponseEntity<AuthResponse>(authResp,HttpStatus.CREATED);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser( @RequestBody User user) throws UserException {
	  
		String email= user.getEmail();
		String password = user.getPassword();
		String firstName= user.getFirstName();
		String lastName=user.getLastName();
		
		User isEmailExist=userRepository.findByEmail(email);
		if(isEmailExist != null) {
			throw new UserException("Email Is Already Used With Another Account");
		}
		
		User createdUser= new User();
		createdUser.setEmail(email);
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		createdUser.setPassword(encoder.encode(password));
		
		User savedUser = userRepository.save(createdUser);
		Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
//		authentication.isAuthenticated()
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token= jwtUtils.generateJwtToken(authentication);
		
		AuthResponse authResp= new AuthResponse(token,"Signup Success");
		return new ResponseEntity<AuthResponse>(authResp,HttpStatus.CREATED);
  }
}