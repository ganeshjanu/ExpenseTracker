package com.friends.order.services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.friends.order.HttpLoggingHandler;
import com.friends.order.beans.Order;
import com.friends.order.beans.Payment;
import com.friends.order.exceptions.ServiceException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.netty.channel.BootstrapHandlers;
import reactor.netty.http.client.HttpClient;



@Service("orderpaymentsvc")
@Profile("external")
public class OrderServicePaymentImpl implements OrderService {
	
	Logger logger = LoggerFactory.getLogger(OrderServicePaymentImpl.class);

	@Autowired
	private WebClient webClient;
	
	
	@Value("${paymentsvcurl}")
	private String paymentSvcURL;
	

	@Override
	@CircuitBreaker(name = "orderservice", fallbackMethod = "fallbackForPaymentSvc")
	public String createOrder(Order order) throws ServiceException {
		Payment payment = new Payment();
		payment.setAmount("1000");
		payment.setMode("cash");
		logger.info("Making call to Payment Service {} " , payment);
		ResponseEntity<Map> response =  webClient.
										post()
										.uri(paymentSvcURL)
										.bodyValue(payment).exchange()
										.flatMap(clientResponse -> clientResponse.toEntity(Map.class))
										.block();
		String paymentId = (String)(response.getBody().get("paymentId"));
		logger.info("Response back from Payment Service {} " , paymentId);
		return paymentId;
	}
	
	public String fallbackForPaymentSvc(Order order, Throwable throwable)  throws ServiceException {
		logger.info("Exception message for Payment Service {} " , throwable.getMessage());
		return "Service is not available : " + throwable.getMessage();
	}
	

}
