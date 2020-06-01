package com.friends.employee;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.friends.employee.beans.form.Address;
import com.friends.employee.beans.form.EmployeeForm;
import com.friends.employee.beans.form.Phone;
import com.friends.employee.exceptions.AppException;
import com.friends.employee.exceptions.ResourceNotFoundException;
import com.friends.employee.services.EmployeeServices;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeServices employeeServices;
	
	private static EmployeeForm responseEmployeeForm;
	
	@Test
	@Order(1)
	public void test_AddEmployee_OK() throws Exception {
		// Add Employee
		EmployeeForm employee = new EmployeeForm();
		employee.setName("Friend");
		employee.setAge(32);
		// Set Address
		Address address = new Address();
		address.setMailingAddress("USA");
		employee.setAddress(address);
		// Set Phone
		Phone phone = new Phone();
		phone.setHomeNum("999-999-9999");
		phone.setMobileNum("888-888-8888");
		employee.setPhone(phone);
		EmployeeForm responseEmployee = null;
		try {
			responseEmployee = employeeServices.addEmployee(employee);
			System.out.println("Employee Id  & Address Id : " + responseEmployee.getId() + " , " + responseEmployee.getAddress().getId());
			responseEmployeeForm = responseEmployee;
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assertions.assertNotNull(responseEmployee);
	}
	
	@Test
	@Order(2)
	public void test_findEmployeeDetailsById_OK() {
		EmployeeForm responseEmployee = null;
		try {
			responseEmployee = employeeServices.findEmployeeById(responseEmployeeForm.getId());
			System.out.println("Find the employee's address : " + responseEmployee.getAddress());
			System.out.println("Find the employee's phone : " + responseEmployee.getPhone());
		} catch (AppException | ResourceNotFoundException e) {
			e.printStackTrace();
		}
		Assertions.assertNotNull(responseEmployee);
	}
	
	@Test
	@Order(3)
	public void test_findEmployeeDetailsByName_OK() {
		EmployeeForm responseEmployee = null;
		try {
			responseEmployee = employeeServices.findEmployeeByName(responseEmployeeForm.getName());
			System.out.println("Find the employee's address : " + responseEmployee.getAddress());
			System.out.println("Find the employee's phone : " + responseEmployee.getPhone());
		} catch (AppException | ResourceNotFoundException e) {
			e.printStackTrace();
		}
		Assertions.assertNotNull(responseEmployee);
	}
	
	
	@Test
	@Order(4)
	public void test_findEmployeeByAddress_OK() {
		EmployeeForm responseEmployee = null;
		try {
			responseEmployee = employeeServices.findEmployeeByAddressId(responseEmployeeForm.getAddress().getId());
			System.out.println("Find employee by address : " + responseEmployee.getAddress());
		} catch (AppException | ResourceNotFoundException e) {
			e.printStackTrace();
		}
		Assertions.assertNotNull(responseEmployee);
	}
	
	@Test
	@Order(5)
	public void test_findEmployeeByPhone_OK() {
		EmployeeForm responseEmployee = null;;
		try {
			responseEmployee = employeeServices.findEmployeeByMobileNum(responseEmployeeForm.getPhone().getMobileNum());
			System.out.println("Find employee by phone : " + responseEmployee.getPhone());
		} catch (AppException | ResourceNotFoundException e) {
			e.printStackTrace();
		}
		Assertions.assertNotNull(responseEmployee);
	}
	
	@Test
	@Order(6)
	public void test_updateEmployee_OK() {
		EmployeeForm responseEmployee = null;;
		try {
			responseEmployeeForm.setName("Java Friends");
			responseEmployee = employeeServices.updateEmployee(responseEmployeeForm);
			System.out.println("Find employee by phone : " + responseEmployee.getPhone());
		} catch (AppException  e) {
			e.printStackTrace();
		}
		Assertions.assertNotNull(responseEmployee);
	}
	
	
	@Test
	@Order(7)
	public void test_deleteEmployee_OK() {
		Map<String, Boolean> response = null;
		try {
			response = employeeServices.deleteEmployeeById(responseEmployeeForm.getId());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Is employee deleted : " + response);
		Assertions.assertEquals(true, response.get("deleted"));
	}
	
	@Test
	@Order(8)
	public void test_AddEmployee_Error() {
		EmployeeForm employee = new EmployeeForm();
		employee.setAge(32);
		EmployeeForm responseEmployee = null;
		try {
			responseEmployee = employeeServices.addEmployee(employee);
		} catch (Exception e) {	}
		Assertions.assertEquals(null, responseEmployee);
	}
}
