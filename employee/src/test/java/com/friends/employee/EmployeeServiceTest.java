package com.friends.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.friends.employee.beans.EmployeeForm;
import com.friends.employee.services.EmployeeServices;

@SpringBootTest()
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeServices employeeServices;
	
	@Test
	public void test_AddEmployee_Success() throws Exception {
		EmployeeForm employee = new EmployeeForm("Ganesh", 64, 1000.0);
		EmployeeForm responseEmployee = employeeServices.addEmployee(employee);
		Assertions.assertEquals(employee, responseEmployee);
	}
	
	@Test
	public void test_AddEmployee_Failed() {
		EmployeeForm employee = new EmployeeForm();
		employee.setAge(32);
		EmployeeForm responseEmployee = null;
		try {
			responseEmployee = employeeServices.addEmployee(employee);
		} catch (Exception e) {	}
		Assertions.assertEquals(null, responseEmployee);
	}

	
}
