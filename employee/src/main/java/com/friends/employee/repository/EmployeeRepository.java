package com.friends.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.friends.employee.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	

}
