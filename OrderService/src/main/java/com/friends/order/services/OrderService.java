package com.friends.order.services;

import com.friends.order.beans.Order;
import com.friends.order.exceptions.ServiceException;

public interface OrderService {
	
	public String createOrder(Order order) throws ServiceException;

}
