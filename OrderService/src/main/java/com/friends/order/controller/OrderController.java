package com.friends.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.friends.order.beans.Order;
import com.friends.order.exceptions.ServiceException;
import com.friends.order.services.OrderService;

@RestController
public class OrderController {
	

	@Autowired
	private OrderService orderSvc;
	
	@PostMapping(path = "/create")
	public Map<String,String> createOrder(@RequestBody Order order) throws ServiceException {
		String orderId = orderSvc.createOrder(order);
		Map<String, String> result = new HashMap<String, String>();
		result.put("order-id", orderId);
		return result;
	}
}
