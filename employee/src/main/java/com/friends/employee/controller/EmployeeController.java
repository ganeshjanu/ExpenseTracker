package com.friends.employee.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.friends.employee.beans.Employee;
import com.friends.employee.services.EmployeeServices;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeServices employeeServices;
	
	@PostMapping(value="/add")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) throws Exception {
		Employee emp= employeeServices.addEmployee(employee);
		return ResponseEntity.ok().body(emp);
	}
	
	@GetMapping(value="/find")
	public ResponseEntity<Employee> getEmployee(@Valid @NotBlank(message ="Employee Id shouldn't be blank") @RequestParam Long empId) throws Exception {
		Employee emp= employeeServices.findEmployeeById(empId);
		return ResponseEntity.ok().body(emp);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@NotBlank @RequestParam(name = "empId") Long employeeId) throws Exception {
		Map<String, Boolean> response = employeeServices.deleteEmployeeById(employeeId);
		return ResponseEntity.ok().body(response);
	}
	

}
