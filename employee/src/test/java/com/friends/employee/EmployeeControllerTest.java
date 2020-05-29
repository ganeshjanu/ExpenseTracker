package com.friends.employee;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.friends.employee.beans.ErrorResponse;
import com.friends.employee.beans.form.Address;
import com.friends.employee.beans.form.EmployeeForm;
import com.friends.employee.beans.form.Phone;
import com.friends.employee.exceptions.ResourceNotFoundException;
import com.friends.employee.services.EmployeeServices;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
	EmployeeServices employeeServices;
	
	
	@Test
	public void test_AddEmployee_OK() throws Exception {
		EmployeeForm employee = new EmployeeForm(null, 35, "Friend", new Address(null, "Montvale"), new Phone(null, "999-999-9999", "888-888-8888"));
		EmployeeForm resEmployeeForm = new EmployeeForm(1L, 35, "Friend", new Address(null, "Montvale"), new Phone(null, "999-999-9999", "888-888-8888"));
		when(employeeServices.addEmployee(employee)).thenReturn(resEmployeeForm);
		ResponseEntity<EmployeeForm> response = restTemplate.postForEntity("/employee", employee, EmployeeForm.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody().getId());
	}
	
	
	@Test
	public void test_MissingEmpName_Fail() throws Exception {
		EmployeeForm employee = new EmployeeForm();
		employee.setAge(32);
		List<String> errorMessage = new ArrayList<String>();
		errorMessage.add("Employee name shouldn't be empty");
		ErrorResponse errorResponse = new ErrorResponse("Validation Failed", errorMessage);
		ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("/employee", employee, ErrorResponse.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(errorResponse, response.getBody());
	}
	
	@Test
	public void test_UpdateEmployee_OK() throws Exception {
		EmployeeForm employee = new EmployeeForm(1L, 35, "Friend", new Address(null, "Montvale"), new Phone(null, "999-999-9999", "888-888-8888"));
		when(employeeServices.updateEmployee(employee)).thenReturn(employee);
		HttpEntity<EmployeeForm> empHttpEntity = new HttpEntity<EmployeeForm>(employee);
		ResponseEntity<EmployeeForm> response = restTemplate.exchange("/employee", HttpMethod.PUT, empHttpEntity, EmployeeForm.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(employee, response.getBody());
	}
	

	@Test
	public void test_deleteEmployee_OK() throws Exception {
		Map<String,Boolean> responseMap = new HashMap<>();
		responseMap.put("deleted", true);
		when(employeeServices.deleteEmployeeById(1L)).thenReturn(responseMap);
		ResponseEntity<Map> response = restTemplate.exchange("/employee?empId=1",  HttpMethod.DELETE, new HttpEntity<String>(new String()), Map.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(response.getBody().get("deleted"), true);
	}
		
	@Test
	public void test_findEmployeeById_OK() throws Exception {
		EmployeeForm employee = new EmployeeForm(1L, 35, "Friend", new Address(null, "Montvale"), new Phone(null, "999-999-9999", "888-888-8888"));
		when(employeeServices.findEmployeeById(1L)).thenReturn(employee);
		ResponseEntity<EmployeeForm> response = restTemplate.getForEntity("/employee?empId=1", EmployeeForm.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
	}
	
	@Test
	public void test_findEmployeeByIdWithNoParam_Fail() throws Exception {
		ResponseEntity<ErrorResponse> response = restTemplate.getForEntity("/employee", ErrorResponse.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		System.out.println(response.getBody());
	}
	
	@Test
	public void test_findEmployeeByName_OK() throws Exception {
		EmployeeForm employee = new EmployeeForm(1L, 35, "Friend", new Address(null, "Montvale"), new Phone(null, "999-999-9999", "888-888-8888"));
		when(employeeServices.findEmployeeByName("Friend")).thenReturn(employee);
		ResponseEntity<EmployeeForm> response = restTemplate.getForEntity("/employee?empName=Friend", EmployeeForm.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
	}
	
		
	@Test
	public void test_EmployeeNotFound_OK() throws Exception {
		when(employeeServices.findEmployeeById(2L)).thenThrow(new ResourceNotFoundException("Employee Id : 2L"));
		ResponseEntity<ResourceNotFoundException> response = restTemplate.getForEntity("/employee?empId=2", ResourceNotFoundException.class);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
	}

}
