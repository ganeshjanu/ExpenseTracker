package com.friends.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.friends.employee.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value = "Select * from employee e where e.name = :empName", nativeQuery = true)
	public Optional<Employee> findByName(@Param("empName") String userName);
}
