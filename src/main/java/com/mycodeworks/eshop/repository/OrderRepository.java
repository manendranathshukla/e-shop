package com.mycodeworks.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycodeworks.eshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
