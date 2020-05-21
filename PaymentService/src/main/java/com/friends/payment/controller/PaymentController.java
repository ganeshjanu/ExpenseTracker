package com.friends.payment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.friends.payment.beans.Payment;
import com.friends.payment.exceptions.RepositoryException;
import com.friends.payment.repository.PaymentRepository;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping(path = "/create", produces = "application/json")
	public Map<String,String> addPayment(@RequestBody Payment payment) throws RepositoryException {
		String paymentId = UUID.randomUUID().toString();
		payment.setId(paymentId);
		paymentRepository.save(payment);
		Map<String,String> result = new HashMap<>();
		result.put("paymentId", paymentId);		
		return result;
	}
}
