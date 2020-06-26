package com.friends.employeemanagement.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeBean implements Serializable{
	
	private static final long serialVersionUID = 782513049068845128L;

	private String name;
	
	private String age;
	
	private String salary;
	
	private int id;

}
