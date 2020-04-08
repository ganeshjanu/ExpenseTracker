package com.friends.employee;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.friends.employee.beans.Employee;
import com.friends.employee.beans.ErrorResponse;
import com.friends.employee.services.EmployeeServices;
import com.friends.employee.services.EmployeeServicesImpl;

import java.util.List;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
	EmployeeServices employeeServices;
	
	@Test
	public void test_AddEmployee_OK() throws Exception {
		Employee employee = new Employee("Prabu", 1L, 35, 1000.0);
		Employee responseEmployee = new Employee("Prabu",  35, 1000.0);
		when(employeeServices.addEmployee(employee)).thenReturn(responseEmployee);
		ResponseEntity<Employee> response = restTemplate.postForEntity("/employee/add", employee, Employee.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(responseEmployee, response.getBody());
	}
	
	@Test
	public void test_MissingEmpName_Fail() throws Exception {
		Employee employee = new Employee();
		employee.setSalary(1000.0);
		employee.setAge(32);
		employee.setId(6L);
		List<String> errorMessage = new ArrayList<String>();
		errorMessage.add("Employee name shouldn't be empty");
		ErrorResponse errorResponse = new ErrorResponse("Validation Failed", errorMessage);
		ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("/employee/add", employee, ErrorResponse.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(errorResponse, response.getBody());
	}
	
	@Test
	public void test_UpdateEmployee_OK() throws Exception {
		Employee employee = new Employee("Prabu", 1L, 35, 2000.0);
		Employee responseEmployee = new Employee("Prabu",  35, 2000.0);
		when(employeeServices.updateEmployee(employee)).thenReturn(responseEmployee);
		ResponseEntity<Employee> response = restTemplate.postForEntity("/employee/update", employee, Employee.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(responseEmployee, response.getBody());
	}
		
		

}
