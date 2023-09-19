package com.mycodeworks.eshop.service;

import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.model.User;



public interface UserService {
	
	public User findUserById(long id) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

	
}
