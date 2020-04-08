package com.friends.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.friends.employee.beans.Employee;
import com.friends.employee.repository.EmployeeRepository;
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
	
	@Test
	public void test_updateEmployee_Success() throws Exception {
		
		//Create New Employee object
		Employee newEmployee = new Employee("GKD",32,1000.0);
		newEmployee.setId(1L);
		Employee newEmployeeCreated = employeeServices.addEmployee(newEmployee);
		
		//Get Employee from database
		Employee getEmployeeObject = employeeServices.findEmployeeById(newEmployeeCreated.getId());
		//Update Salary
		getEmployeeObject.setSalary(2000.0);
		
		//Prepare response object for comparison
		Employee expectedEmployeeResponse = new Employee("GKD",32,2000.0);
		expectedEmployeeResponse.setId(getEmployeeObject.getId());
		
		//Update Employee
		Employee actualEmployeeResponse = employeeServices.updateEmployee(getEmployeeObject);
		
		//Assert Equals if the updated employee object is same as the actual update object
		Assertions.assertEquals(expectedEmployeeResponse, actualEmployeeResponse);
	}
	
	@Test
	public void test_updateEmployee_Failure() throws Exception {
		String expectedErrorMessage = "Employee not available with the following Employee Id : 444";
		String actualErrorMessage = null;
		
		//Create New Employee object
		Employee newEmployee = new Employee("GKD",32,1000.0);
		newEmployee.setId(444L);
		
		
		Employee actualEmployeeResponse = null;
		try {		
		//Update Employee
			actualEmployeeResponse = employeeServices.updateEmployee(newEmployee);
		}catch (Exception e) {
			
			actualErrorMessage = e.getMessage().toString();			
		}
		
		//Assert Equals if the updated employee object is same as the actual update object
		Assertions.assertEquals(null, actualEmployeeResponse);
		
		//Assert expected and actual error message
		Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
	}

	
}
