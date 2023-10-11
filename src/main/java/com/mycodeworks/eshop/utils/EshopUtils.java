package com.mycodeworks.eshop.utils;

public class EshopUtils {
	
	public static String cleanJwt(String jwt) {
		return jwt.split(" ")[1].trim();
	}

}
