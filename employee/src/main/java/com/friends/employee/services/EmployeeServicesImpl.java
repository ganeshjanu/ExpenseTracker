package com.friends.employee.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friends.employee.beans.Employee;
import com.friends.employee.exceptions.ResourceNotFoundException;
import com.friends.employee.repository.EmployeeRepository;

@Service
public class EmployeeServicesImpl implements EmployeeServices {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) throws Exception {
		return employeeRepository.saveAndFlush(employee);
	}

	@Override
	public Employee findEmployeeById(Long empId) throws ResourceNotFoundException {
		return employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Id : " + empId));
	}

	@Override
	public Map<String, Boolean> deleteEmployeeById(Long empId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Id : " + empId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response =  new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
