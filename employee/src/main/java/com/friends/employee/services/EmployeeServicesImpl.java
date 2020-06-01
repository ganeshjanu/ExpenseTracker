package com.friends.employee.services;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friends.employee.beans.dto.Address;
import com.friends.employee.beans.dto.Employee;
import com.friends.employee.beans.dto.Phone;
import com.friends.employee.beans.form.EmployeeForm;
import com.friends.employee.exceptions.AppException;
import com.friends.employee.exceptions.ResourceNotFoundException;
import com.friends.employee.repository.AddressRepository;
import com.friends.employee.repository.EmployeeRepository;
import com.friends.employee.repository.PhoneRepository;

@Service
public class EmployeeServicesImpl implements EmployeeServices {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public EmployeeForm addEmployee(EmployeeForm employee) throws AppException  {
		Employee emp = new Employee();
		try {
			emp.setAge(employee.getAge());
			emp.setName(employee.getName());
			Address address = new Address(null,employee.getAddress().getMailingAddress(), null);
			emp.setEmpAddress(address);
			Phone phone = new Phone(null, employee.getPhone().getMobileNum(), employee.getPhone().getHomeNum(), null);
			phone.setEmployeeInPhone(emp);
			emp.setPhone(phone);
			emp =  employeeRepository.saveAndFlush(emp);
			employee.setId(emp.getId());
			employee.getAddress().setId(emp.getEmpAddress().getId());
			employee.getPhone().setId(emp.getPhone().getId());
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

	@Override
	public EmployeeForm findEmployeeByMobileNum(String mobileNum) throws ResourceNotFoundException, AppException {
		Phone phone = phoneRepository.findByMobileNum(mobileNum).orElseThrow(() -> new ResourceNotFoundException("Employee is not found based on mobile no : " + mobileNum));
		EmployeeForm employeeForm = new EmployeeForm();
		try {
			BeanUtils.copyProperties(phone.getEmployeeInPhone(), employeeForm);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new AppException(e.getMessage());
		}
		return employeeForm;
	}

	@Override
	public EmployeeForm findEmployeeByAddressId(Long addressId) throws ResourceNotFoundException, AppException {
		Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Employee is not found based on address id : " + addressId));
		EmployeeForm employeeForm = new EmployeeForm();
		try {
			BeanUtils.copyProperties(address.getEmployee(), employeeForm);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new AppException(e.getMessage());
		}
		return employeeForm;
	}

}
