package com.friends.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.friends.employee.beans.Employee;
import com.friends.employee.services.EmployeeServices;

@SpringBootTest()
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeServices employeeServices;
	
	@Test
	public void test_AddEmployee_Success() throws Exception {
		Employee employee = new Employee("Ganesh", 32, 1000.0);
		Employee responseEmployee = employeeServices.addEmployee(employee);
		employee.setId(1L);
		Assertions.assertEquals(employee, responseEmployee);
	}
	
	@Test
	public void test_AddEmployee_Failed() {
		Employee employee = new Employee();
		employee.setAge(32);
		Employee responseEmployee = null;
		try {
			responseEmployee = employeeServices.addEmployee(employee);
		} catch (Exception e) {	}
		Assertions.assertEquals(null, responseEmployee);
	}


}
