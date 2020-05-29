package com.friends.employee.beans.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
	
	@Id
	@Column(name = "PHONE_ID")
	private Long id;
	
	@Column(name = "MOBILE_NUM")
	private String mobileNum;
	
	@Column(name = "HOME_NUM")
	private String homeNum;
	
	@OneToOne
    @MapsId
	private Employee employee;
}
