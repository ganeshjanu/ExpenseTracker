package com.friends.employee.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.friends.employee.beans.EmployeeForm;
import com.friends.employee.exceptions.AppException;
import com.friends.employee.exceptions.ResourceNotFoundException;
import com.friends.employee.services.EmployeeServices;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeServices employeeServices;
	
	@PostMapping(value="/add")
	public ResponseEntity<EmployeeForm> addEmployee(@Valid @RequestBody EmployeeForm employee) throws AppException {
		//dsd
		EmployeeForm emp= employeeServices.addEmployee(employee);
		return ResponseEntity.ok().body(emp);
	}
	
	@GetMapping(value="/find", params = {"empId"} )
	public ResponseEntity<EmployeeForm> getEmployee( @RequestParam(value = "empId")   @NotBlank  Long employeeId) throws ResourceNotFoundException, AppException {
		EmployeeForm emp= employeeServices.findEmployeeById(employeeId);
		return ResponseEntity.ok().body(emp);
	}
	
	@GetMapping(value="/find", params = {"empName"})
	public ResponseEntity<EmployeeForm> getEmployee( @RequestParam(value = "empName")   @NotBlank  String employeeName) throws ResourceNotFoundException, AppException {
		EmployeeForm emp= employeeServices.findEmployeeByName(employeeName);
		return ResponseEntity.ok().body(emp);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@RequestParam(name = "empId") @NotBlank  Long employeeId) throws ResourceNotFoundException {
		Map<String, Boolean> response = employeeServices.deleteEmployeeById(employeeId);
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping(value="/update")
	public ResponseEntity<EmployeeForm> updateEmployee(@Valid @RequestBody EmployeeForm employee) throws AppException {
		EmployeeForm emp= employeeServices.updateEmployee(employee);
		return ResponseEntity.ok().body(emp);
	}
	

}