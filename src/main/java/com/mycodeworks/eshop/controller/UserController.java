package com.mycodeworks.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycodeworks.eshop.exception.UserException;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.service.UserService;
import com.mycodeworks.eshop.utils.EshopUtils;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt)throws UserException{
		
		User user = userService.findUserProfileByJwt(EshopUtils.cleanJwt(jwt));
		
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}

}
