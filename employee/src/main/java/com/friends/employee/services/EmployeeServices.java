package com.friends.employee.services;

import java.util.Map;

import com.friends.employee.beans.Employee;

//Test comment
public interface EmployeeServices {
	
	public Employee addEmployee(Employee employee) throws Exception;
	
	public Employee updateEmployee(Employee employee) throws Exception;
	
	public Employee findEmployeeById(Long empId) throws Exception;
	
	public Employee findEmployeeByName(String empName) throws Exception;
	
	public Map<String, Boolean> deleteEmployeeById(Long empId) throws Exception;

}
