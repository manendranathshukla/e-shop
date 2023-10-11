package com.mycodeworks.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycodeworks.eshop.exception.OrderException;
import com.mycodeworks.eshop.model.Address;
import com.mycodeworks.eshop.model.Order;
import com.mycodeworks.eshop.model.User;
import com.mycodeworks.eshop.repository.CartRepository;
import com.mycodeworks.eshop.repository.OrderItemRepository;
import com.mycodeworks.eshop.repository.OrderRepository;
import com.mycodeworks.eshop.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	@Autowired private OrderRepository ordRepo;
	@Autowired private UserRepository userRepo;
	@Autowired private OrderItemRepository ordItemRepo;
	@Autowired private CartRepository cartRepo;
	@Autowired private CartService cartItemService;
	@Autowired private ProductService productService;
	
	
	
	@Override
	public Order createOrder(User user, Address shippingAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> userOrderHistory(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order cancledOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub

	}

}
