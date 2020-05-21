package com.friends.employee;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.friends.employee.beans.EmployeeForm;
import com.friends.employee.beans.ErrorResponse;
import com.friends.employee.services.EmployeeServices;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
	EmployeeServices employeeServices;
	
	@Test
	public void test_AddEmployee_OK() throws Exception {
		EmployeeForm employee = new EmployeeForm("Prabu",  35, 1000.0);
		EmployeeForm responseEmployee = new EmployeeForm("Prabu",  35, 1000.0);
		when(employeeServices.addEmployee(employee)).thenReturn(responseEmployee);
		ResponseEntity<EmployeeForm> response = restTemplate.postForEntity("/employee/add", employee, EmployeeForm.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(responseEmployee, response.getBody());
	}
	
	@Test
	public void test_MissingEmpName_Fail() throws Exception {
		EmployeeForm employee = new EmployeeForm();
		employee.setSalary(1000.0);
		employee.setAge(32);
		List<String> errorMessage = new ArrayList<String>();
		errorMessage.add("Employee name shouldn't be empty");
		ErrorResponse errorResponse = new ErrorResponse("Validation Failed", errorMessage);
		ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("/employee/add", employee, ErrorResponse.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(errorResponse, response.getBody());
	}
	
	@Test
	public void test_UpdateEmployee_OK() throws Exception {
		EmployeeForm employee = new EmployeeForm("Prabu", 35, 2000.0);
		EmployeeForm responseEmployee = new EmployeeForm("Prabu",  35, 2000.0);
		when(employeeServices.updateEmployee(employee)).thenReturn(responseEmployee);
		ResponseEntity<EmployeeForm> response = restTemplate.postForEntity("/employee/update", employee, EmployeeForm.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(responseEmployee, response.getBody());
	}
		
		

}
