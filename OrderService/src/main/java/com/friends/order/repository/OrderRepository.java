package com.friends.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.friends.order.beans.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {}
	

