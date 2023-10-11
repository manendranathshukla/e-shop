package com.mycodeworks.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycodeworks.eshop.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query("SELECT c From Cart c Where c.user.id = :userId")
	Cart findUserById(@Param("userId") Long userId);

}
