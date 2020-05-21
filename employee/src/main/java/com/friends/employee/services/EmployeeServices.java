package com.friends.employee.services;

import java.util.Map;

import com.friends.employee.beans.EmployeeForm;
import com.friends.employee.exceptions.AppException;
import com.friends.employee.exceptions.ResourceNotFoundException;


public interface EmployeeServices {
	
	public EmployeeForm addEmployee(EmployeeForm employee) throws AppException ;
	
	public EmployeeForm updateEmployee(EmployeeForm employee) throws  AppException;
	
	public EmployeeForm findEmployeeById(Long empId) throws ResourceNotFoundException, AppException;
	
	public EmployeeForm findEmployeeByName(String empName) throws ResourceNotFoundException, AppException;
	
	public Map<String, Boolean> deleteEmployeeById(Long empId) throws ResourceNotFoundException;

}
