package com.friends.employee.services;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friends.employee.beans.Employee;
import com.friends.employee.beans.EmployeeForm;
import com.friends.employee.exceptions.AppException;
import com.friends.employee.exceptions.ResourceNotFoundException;
import com.friends.employee.repository.EmployeeRepository;

@Service
public class EmployeeServicesImpl implements EmployeeServices {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeForm addEmployee(EmployeeForm employee) throws AppException  {
		Employee emp = new Employee();
		try {
			emp.setAge(employee.getAge());
			emp.setName(employee.getName());
			emp.setSalary(employee.getSalary());
			emp =  employeeRepository.saveAndFlush(emp);
			BeanUtils.copyProperties(emp, employee);
		} catch (Exception exception) {
			throw new AppException(exception.getMessage());
		}
		return employee;
	}

	@Override
	public EmployeeForm findEmployeeById(Long empId) throws ResourceNotFoundException, AppException {
		Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Id : " + empId));
		EmployeeForm employeeForm = new EmployeeForm();
		try {
			BeanUtils.copyProperties(emp, employeeForm);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new AppException(e.getMessage());
		}
		return employeeForm;
	}
	
	@Override
	public EmployeeForm findEmployeeByName(String empName) throws ResourceNotFoundException, AppException {
		Employee emp =  employeeRepository.findByName(empName).orElseThrow(() -> new ResourceNotFoundException("Employee Name : " + empName));
		EmployeeForm employeeForm = new EmployeeForm();
		try {
			BeanUtils.copyProperties(emp, employeeForm);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new AppException(e.getMessage());
		}
		return employeeForm;
	}

	@Override
	public Map<String, Boolean> deleteEmployeeById(Long empId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Id : " + empId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response =  new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public EmployeeForm updateEmployee(EmployeeForm employee) throws AppException {
		return addEmployee(employee);
	}

}