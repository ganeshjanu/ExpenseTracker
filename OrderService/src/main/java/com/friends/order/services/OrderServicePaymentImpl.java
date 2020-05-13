package com.friends.order.services;

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.friends.order.beans.Order;
import com.friends.order.beans.Payment;
import com.friends.order.exceptions.ServiceException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service("orderpaymentsvc")
@Profile("local")
public class OrderServicePaymentImpl implements OrderService {

	private RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}
	
	@Value("${paymentsvcurl}")
	private String paymentSvcURL;
	

	@Override
	@CircuitBreaker(name = "orderservice", fallbackMethod = "fallbackForPaymentSvc")
	public String createOrder(Order order) throws ServiceException {
		Payment payment = new Payment();
		payment.setAmount("1000");
		payment.setMode("cash");
		ResponseEntity<Map>  response = restTemplate.postForEntity(paymentSvcURL, payment, Map.class);
		return (String)(response.getBody().get("paymentId"));
	}
	
	public String fallbackForPaymentSvc(Order order, Throwable throwable)  throws ServiceException {
		System.out.println(LocalDateTime.now() + " Exception on Payment svc : " + throwable.getMessage());
		return "Service is not available : " + throwable.getMessage();
	}
	

}
