package com.friends.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

import com.friends.employee.beans.dto.Address;
import com.friends.employee.beans.dto.Employee;
import com.friends.employee.beans.dto.Phone;
import com.friends.employee.repository.AddressRepository;
import com.friends.employee.repository.EmployeeRepository;
import com.friends.employee.repository.PhoneRepository;

@SpringBootTest
public class EmployeeRepositoryTest {
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void test_AddPhoneWithNoEmployee_OK() {
		Phone phone = new Phone(100L,"888-888-8888", "999-999-9999",null);
		JpaSystemException exception = null;
		try {
			phoneRepository.saveAndFlush(phone);
		} catch(JpaSystemException ex) {
			exception = ex;
		}
		Assertions.assertNotNull(exception);
	}
	
	@Test
	public void test_AddAddressThenEmployee_OK() {
		Address address = new Address(200L, "Friends Home", null);
		Address respAddress = addressRepository.saveAndFlush(address);
		Employee employee = new Employee(300L, 25, "Friends", respAddress, null);
		Assertions.assertEquals(respAddress.getId(), employeeRepository.saveAndFlush(employee).getEmpAddress().getId());
	}


}
