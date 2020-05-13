package com.friends.employee.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeForm {

	@NotBlank(message = "Employee name shouldn't be empty")
	private String name;
	@Min(value = 20, message = "Employee's age should be atleast 20 years old ")
	private Integer age;
	private Double salary;
	private Long id;
	public EmployeeForm(@NotBlank(message = "Employee name shouldn't be empty") String name,
			@Min(value = 20, message = "Employee's age should be atleast 20 years old ") Integer age, Double salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	
}
