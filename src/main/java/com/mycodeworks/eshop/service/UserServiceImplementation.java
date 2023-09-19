package com.mycodeworks.eshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.jwt.JwtUtils;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired private JwtUtils jwtProvider;
	@Override
	public User findUserById(long id) throws UserException {
		Optional<User> user= userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("User not found with id-"+id);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		
		String email= jwtProvider.getUserNameFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserException("User not found with email-"+email);
			
		}
		return user;
		
		
	}

}
