package com.friends.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.friends.employeemanagement.beans.EmployeeBean;
import com.friends.employeemanagement.beans.ResponseBean;
import com.friends.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseBean addEmployee(@RequestBody EmployeeBean employeeBean) {
		return employeeService.addEmployee(employeeBean);
	}

}
