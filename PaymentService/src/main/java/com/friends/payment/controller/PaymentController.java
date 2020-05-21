package com.friends.payment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.friends.payment.beans.Payment;
import com.friends.payment.exceptions.RepositoryException;
import com.friends.payment.repository.PaymentRepository;

@RestController
public class PaymentController {
	
	private Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping(path = "/create", produces = "application/json")
	public Map<String,String> addPayment(@RequestBody Payment payment) throws RepositoryException {
		logger.info("Request the payment  {} " , payment);
		String paymentId = UUID.randomUUID().toString();
		payment.setId(paymentId);
		paymentRepository.save(payment);
		Map<String,String> result = new HashMap<>();
		result.put("paymentId", paymentId);	
		logger.info("Return the payment id {} " , paymentId);
		return result;
	}
}
