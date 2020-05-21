package com.friends.order.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.friends.order.beans.Order;
import com.friends.order.exceptions.ServiceException;
import com.friends.order.repository.OrderRepository;

@Service("ordersvc")
@Profile("internal")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public String createOrder(Order order) throws ServiceException {
		String orderId = UUID.randomUUID().toString();
		order.setId(orderId);
		String paymentId = UUID.randomUUID().toString();
		order.setPaymentId(paymentId);
		try {
			orderRepository.save(order);
		} catch (DataAccessException exception) {
			throw new ServiceException("Order can't be created");
		}
		return orderId;
	}

}
