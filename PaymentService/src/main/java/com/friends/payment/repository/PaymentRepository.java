package com.friends.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.friends.payment.beans.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {}
