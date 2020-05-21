package com.friends.order.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
	
	@Id
	private String id;
	
	private String customerName;
	
	private Double productName;
	
	private String itemName;
	
	private String paymentId;

	
	

}
