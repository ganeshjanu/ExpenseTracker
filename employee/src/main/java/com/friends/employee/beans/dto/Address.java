package com.friends.employee.beans.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "MAIL_ADDRESS")
	private String mailingAddress;
	
	@OneToOne(mappedBy = "empAddress" )
	private Employee employee;

	@Override
	public String toString() {
		return "Address [addressId=" + id + ", mailingAddress=" + mailingAddress + "]";
	}
	
	
}
